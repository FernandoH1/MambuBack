package com.mambu.back.service.Impl;

import com.mambu.back.config.WebClientConfig;
import com.mambu.back.headers.GetHeaders;
import com.mambu.back.model.dto.active.ApproveLoanDTO;
import com.mambu.back.model.dto.active.BlockLoanAccountDTO;
import com.mambu.back.model.dto.active.BlockResponseLoanAccountDTO;
import com.mambu.back.model.dto.active.LoanAccountDTO;
import com.mambu.back.service.LoanAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Mono;

@Service
public class LoanAccountServiceImpl implements LoanAccountService {

    @Autowired
    private WebClientConfig webClientConfig;

    @Autowired
    private GetHeaders headers;

    @Override
    public Mono<LoanAccountDTO> createLoanAccount(LoanAccountDTO loanAccountDTO) {
        return webClientConfig.webClient().post()
                .uri(uriBuilder -> uriBuilder
                        .path("/loans")
                        .build())
                .body(Mono.just(loanAccountDTO), LoanAccountDTO.class)
                .header("Accept", headers.headers().getAccept())
                .header("Content-Type", headers.headers().getContentType())
                .header("IdempotencyKey", headers.headers().getIdempotencyKey())
                .retrieve().bodyToMono(LoanAccountDTO.class).onErrorResume(e -> {
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
    public Mono<LoanAccountDTO> approveLoanAccount(String id) {
        var approveLoanDTO = ApproveLoanDTO.builder().action("APPROVE").notes("Se realizo aprovacion via Back").build();
        return webClientConfig.webClient().post()
                .uri(uriBuilder -> uriBuilder
                        .path("/loans/"+id+":changeState")
                        .build())
                .body(Mono.just(approveLoanDTO), LoanAccountDTO.class)
                .header("Accept", headers.headers().getAccept())
                .header("Content-Type", headers.headers().getContentType())
                .header("IdempotencyKey", headers.headers().getIdempotencyKey())
                .retrieve().bodyToMono(LoanAccountDTO.class).onErrorResume(e -> {
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
    public Mono<BlockResponseLoanAccountDTO> blockLoanAccount(BlockLoanAccountDTO blockLoanAccountDTO, String id) {
        blockLoanAccountDTO.setNotes("Se bloqueo la cuenta via Back");
        return webClientConfig.webClient().post()
                .uri(uriBuilder -> uriBuilder
                        .path("/loans/" + id + "/lock-transactions")
                        .build())
                .body(Mono.just(blockLoanAccountDTO), BlockLoanAccountDTO.class)
                .header("Accept", headers.headers().getAccept())
                .header("Content-Type", headers.headers().getContentType())
                .header("IdempotencyKey", headers.headers().getIdempotencyKey())
                .retrieve().bodyToMono(BlockResponseLoanAccountDTO.class).onErrorResume(e -> {
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
    public Mono<LoanAccountDTO> searchBlockByID(String id) {
        return webClientConfig.webClient().get()
                .uri(uriBuilder -> uriBuilder
                        .path("/loans/" + id)
                        .build())
                .header("Accept", headers.headers().getAccept())
                .retrieve().bodyToMono(LoanAccountDTO.class).onErrorResume(e -> {
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
