package com.mambu.back.service.Impl;

import com.mambu.back.config.WebClientConfig;
import com.mambu.back.headers.GetHeaders;
import com.mambu.back.model.dto.active.ActiveInterestRateDTO;
import com.mambu.back.model.dto.active.common.AccLoanDTO;
import com.mambu.back.model.dto.passive.Account;
import com.mambu.back.model.dto.passive.Interest;
import com.mambu.back.model.dto.passive.InterestBody;
import com.mambu.back.service.InterestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class InterestServiceImpl implements InterestService {

    @Autowired
    private WebClientConfig webClientConfig;

    @Autowired
    private GetHeaders headers;

    @Override
    public Flux<Account> searchInterest(Interest interest, String offset, String limit, String paginationDetails, String detailsLevel) {
        return webClientConfig.webClient().post()
                .uri(uriBuilder -> uriBuilder
                        .path("/deposits:search")
                        .queryParam("offset", offset)
                        .queryParam("limit", limit)
                        .queryParam("paginationDetails", paginationDetails)
                        .queryParam("detailsLevel", detailsLevel)
                        .build())
                .body(Mono.just(interest), Interest.class)
                .header("Accept", headers.headers().getAccept())
                .header("Content-Type", headers.headers().getContentType())
                .retrieve().bodyToFlux(Account.class).onErrorResume(e -> {
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
    public Mono<Object> forceInterest(InterestBody interestBody, String id) {
        return webClientConfig.webClient().post()
                .uri(uriBuilder -> uriBuilder
                        .path("/deposits/" + id + ":applyInterest")
                        .build())
                .body(Mono.just(interestBody), InterestBody.class)
                .header("Accept", headers.headers().getAccept())
                .header("Content-Type", headers.headers().getContentType())
                .header("IdempotencyKey", headers.headers().getIdempotencyKey())
                .exchangeToMono(r -> Mono.empty()).onErrorResume(e -> {
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
    public Mono<Object> updateInterestLoanAccount(ActiveInterestRateDTO activeInterestRateDTO, String id) {

        return webClientConfig.webClient().post()
                .uri(uriBuilder -> uriBuilder
                        .path("/loans/" + id + ":changeInterestRate")
                        .build())
                .body(Mono.just(activeInterestRateDTO), ActiveInterestRateDTO.class)
                .header("Accept", headers.headers().getAccept())
                .header("Content-Type", headers.headers().getContentType())
                .header("IdempotencyKey", headers.headers().getIdempotencyKey())
                .retrieve()
                .bodyToMono(Object.class)
                .onErrorResume(e -> {
                    var responseException = (WebClientResponseException) e;
                    if (e instanceof WebClientResponseException) {
                        if (responseException.getStatusCode().is4xxClientError()) {
                            if (responseException.getStatusCode().value() == 400) {
                                return Mono.error(new Throwable(responseException.getResponseBodyAsString()));
                            }
                            return Mono.error(new Throwable(responseException.getResponseBodyAsString()));
                        }
                    }
                    return Mono.error(new Throwable(responseException.getResponseBodyAsString()));
                });
    }
}
