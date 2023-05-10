package com.mambu.back.service.Impl;

import com.mambu.back.config.WebClientConfig;
import com.mambu.back.headers.GetHeaders;
import com.mambu.back.model.dto.active.ApproveLoanDTO;
import com.mambu.back.model.dto.active.DisbursementDTO;
import com.mambu.back.model.dto.active.DisbursementResponseDTO;
import com.mambu.back.model.dto.active.LoanAccountDTO;
import com.mambu.back.service.DisbursementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Mono;

@Service
public class DisbursementServiceImpl implements DisbursementService {

    @Autowired
    private WebClientConfig webClientConfig;

    @Autowired
    private GetHeaders headers;

    @Override
    public Mono<DisbursementResponseDTO> doDisbursement(String id) {
        var disbursementDTO = DisbursementDTO.builder().notes("Se realizo el Desembolso Via Back").build();
        return webClientConfig.webClient().post()
                .uri(uriBuilder -> uriBuilder
                        .path("/loans/"+id+"/disbursement-transactions")
                        .build())
                .body(Mono.just(disbursementDTO), LoanAccountDTO.class)
                .header("Accept", headers.headers().getAccept())
                .header("Content-Type", headers.headers().getContentType())
                .header("IdempotencyKey", headers.headers().getIdempotencyKey())
                .retrieve().bodyToMono(DisbursementResponseDTO.class).onErrorResume(e -> {
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
