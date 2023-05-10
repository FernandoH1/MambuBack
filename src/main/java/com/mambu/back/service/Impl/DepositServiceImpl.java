package com.mambu.back.service.Impl;

import com.mambu.back.config.WebClientConfig;
import com.mambu.back.headers.GetHeaders;
import com.mambu.back.model.dto.passive.Deposit;
import com.mambu.back.model.dto.passive.Transaction;
import com.mambu.back.service.DepositService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class DepositServiceImpl implements DepositService {

    @Autowired
    private WebClientConfig webClientConfig;

    @Autowired
    private GetHeaders headers;

    @Override
    public Mono<Deposit> doDeposit(Deposit deposit, String id) {
        return webClientConfig.webClient().post()
                .uri(uriBuilder -> uriBuilder
                        .path("/deposits/" + id + "/deposit-transactions")
                        .build())
                .body(Mono.just(deposit), Deposit.class)
                .header("Accept", headers.headers().getAccept())
                .header("Content-Type", headers.headers().getContentType())
                .header("IdempotencyKey", headers.headers().getIdempotencyKey())
                .retrieve().bodyToMono(Deposit.class).onErrorResume(e -> {
                    var responseException = (WebClientResponseException) e;
                    if (e instanceof WebClientResponseException) {
                        if (responseException.getStatusCode().is4xxClientError()) {
                            return Mono.error(new Throwable(responseException.getResponseBodyAsString()));
                        }
                    }

                    return Mono.error(new Throwable(responseException.getResponseBodyAsString()));
                });
    }

    @Override
    public Flux<Deposit> doTransactionDeposit(Transaction transaction, String offset, String limit, String paginationDetails, String detailsLevel) {
        return webClientConfig.webClient().post()
                .uri(uriBuilder -> uriBuilder
                        .path("/deposits/transactions:search")
                        .build())
                .body(Mono.just(transaction), Transaction.class)
                .header("Accept", headers.headers().getAccept())
                .header("Content-Type", headers.headers().getContentType())
                .retrieve().bodyToFlux(Deposit.class).onErrorResume(e -> {
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
