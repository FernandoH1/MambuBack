package com.mambu.back.service.Impl;

import com.mambu.back.config.WebClientConfig;
import com.mambu.back.headers.GetHeaders;
import com.mambu.back.model.dto.passive.Transaction;
import com.mambu.back.model.dto.passive.Withdrawal;
import com.mambu.back.service.WithdrawalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class WithdrawalServiceImpl implements WithdrawalService {

    @Autowired
    private WebClientConfig webClientConfig;

    @Autowired
    private GetHeaders headers;

    @Override
    public Mono<Withdrawal> doWithdrawal(Withdrawal withdrawal, String id) {
        return webClientConfig.webClient().post()
                .uri(uriBuilder -> uriBuilder
                        .path("/deposits/" + id + "/withdrawal-transactions")
                        .build())
                .body(Mono.just(withdrawal), Withdrawal.class)
                .header("Accept", headers.headers().getAccept())
                .header("Content-Type", headers.headers().getContentType())
                .header("IdempotencyKey", headers.headers().getIdempotencyKey())
                .retrieve().bodyToMono(Withdrawal.class).onErrorResume(e -> {
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
    public Flux<Withdrawal> doTransactionWithdrawal(Transaction transaction, String offset, String limit, String paginationDetails, String detailsLevel) {
        return webClientConfig.webClient().post()
                .uri(uriBuilder -> uriBuilder
                        .path("/deposits/transactions:search")
                        .build())
                .body(Mono.just(transaction), Transaction.class)
                .header("Accept", headers.headers().getAccept())
                .header("Content-Type", headers.headers().getContentType())
                .retrieve().bodyToFlux(Withdrawal.class).onErrorResume(e -> {
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
