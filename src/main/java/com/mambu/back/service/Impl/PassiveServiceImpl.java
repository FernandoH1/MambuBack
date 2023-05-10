package com.mambu.back.service.Impl;

import com.mambu.back.config.WebClientConfig;
import com.mambu.back.headers.GetHeaders;
import com.mambu.back.model.*;
import com.mambu.back.model.dto.passive.*;
import com.mambu.back.service.PassiveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;

@Service
public class PassiveServiceImpl implements PassiveService {

    @Autowired
    private WebClientConfig webClientConfig;

    @Autowired
    private GetHeaders headers;

    @Autowired
    AccountServiceImpl accountService;

    @Autowired
    ClientServiceImpl clientService;

    @Autowired
    ProductServiceImpl productService;

    @Autowired
    InterestServiceImpl interestService;

    @Override
    public Mono<OnBoardingClient> onBoarding(AccountClient onBoarding) {
        Client client1 = new Client();
        client1.setFirstName(onBoarding.getFirstName());
        client1.setLastName(onBoarding.getLastName());
        client1.setPersonalizados(onBoarding.getPersonalizados());
        return clientService.createClient(client1).flatMap(client2 ->
                productService.getProductByID(onBoarding.getProductId(), "0", "10", "OFF", "FULL").flatMap(product1 -> {
                    var account = Account.builder()
                            .accountHolderKey(client2.getEncodedKey())
                            .accountHolderType(onBoarding.getAccountHolderType())
                            .name(onBoarding.getName())
                            .productTypeKey(product1.getEncodedKey())
                            .notes(onBoarding.getNotes())
                            .accountState(onBoarding.getAccountState())
                            .accountType(product1.getType())
                            .currencyCode(product1.getCurrencySettings().getCurrencies()[0].getCurrencyCode())
                            .build();
                    return accountService.createAccount(account).map(account1 ->
                            OnBoardingClient.builder().cliente(client2).account(account1).build()
                    );
                })
        );
    }

    @Override
    public Mono<SeizeAccount> seizureAccounts(BlockRequest blockRequest, String id) {
        var block = BlockFundsBody.builder()
                .amount(blockRequest.getAmount())
                .externalReferenceId(blockRequest.getExternalReferenceId())
                .notes(blockRequest.getNotes())
                .build();
        return accountService.blockFunds(block, id).flatMap(blockFunds -> {
                    var seizedAccountBody = SeizeAccountBody.builder()
                            .amount(blockRequest.getAmount())
                            .blockId(block.getExternalReferenceId())
                            .externalId(blockRequest.getExternalId())
                            .notes(blockRequest.getNoteSeized())
                            .transactionChannelId(blockRequest.getTransactionChannelId())
                            .build();
                    return accountService.seizeAccount(seizedAccountBody, id);
                }
        );
    }

    private String currentDate() {
        OffsetDateTime date = OffsetDateTime.now(ZoneOffset.of("-05:00")).minusSeconds(1);
        String dateNow = date.toString();
        return dateNow;
    }

    @Override
    public Flux<Object> applyInterest(InterestApply interestApply, String offset, String limit, String paginationDetails, String detailsLevel) {
        var interest = Interest.builder()
                .filterCriteria(interestApply.getFilterCriteria())
                .build();
        return interestService.searchInterest(interest, offset, limit, paginationDetails, detailsLevel).flatMap(data -> {
            var intbody = InterestBody.builder()
                    .interestApplicationDate(currentDate())
                    .notes(interestApply.getNotes())
                    .build();
            return interestService.forceInterest(intbody, data.getId());
        });
    }

    @Override
    public Mono<BlockAccountSearch> searchBlockAccount(BlockAccountBodyReq blockAccountBodyReq, String id, String detailsLevel) {
        var block = BlockAccount.builder()
                .action(blockAccountBodyReq.getAction())
                .notes(blockAccountBodyReq.getNotes())
                .build();
        return accountService.blockAccount(block, id).flatMap(data ->
                accountService.getBlockAccountByID(data.getId(), detailsLevel)
        );
    }

    @Override
    public Flux<TransactionDTO> getLastTransactions(String id, String offset, String limit, String paginationDetails, String detailsLevel) {
        return webClientConfig.webClient().get()
                .uri(uriBuilder -> uriBuilder
                        .path("/deposits/" + id + "/transactions")
                        .queryParam("offset", offset)
                        .queryParam("limit", limit)
                        .queryParam("paginationDetails", paginationDetails)
                        .queryParam("detailsLevel", detailsLevel)
                        .build())
                .header("Accept", headers.headers().getAccept())
                .retrieve().bodyToFlux(TransactionDTO.class).onErrorResume(e -> {
                    var responseException = (WebClientResponseException) e;
                    if (e instanceof WebClientResponseException) {
                        if (responseException.getStatusCode().is4xxClientError()) {
                            return Mono.error(new Throwable(responseException.getResponseBodyAsString()));
                        }
                    }

                    return Mono.error(new Throwable(responseException.getResponseBodyAsString()));
                });
    }


}
