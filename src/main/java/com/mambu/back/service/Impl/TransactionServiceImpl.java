package com.mambu.back.service.Impl;

import com.mambu.back.commonClasses.transaction.FilterCriteria;
import com.mambu.back.commonClasses.transaction.SortingCriteria;
import com.mambu.back.config.WebClientConfig;
import com.mambu.back.headers.GetHeaders;
import com.mambu.back.model.dto.active.common.*;
import com.mambu.back.model.dto.active.*;
import com.mambu.back.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

@Service
public class TransactionServiceImpl implements TransactionService {
    @Autowired
    private WebClientConfig webClientConfig;

    @Autowired
    private GetHeaders headers;

    public SearchDTO crearDisbursementFilter(String id){
        var filtroDesembolso = FilterCriteria.builder()
                .field("type")
                .operator("EQUALS")
                .value("DISBURSEMENT")
                .build();
        var filtroID = FilterCriteria.builder()
                .field("parentAccountKey")
                .operator("EQUALS")
                .value(id)
                .build();
        var filtroNoReversado = FilterCriteria.builder()
                .field("adjustmentTransactionKey")
                .operator("EMPTY")
                .build();
        List<FilterCriteria> filterCriteria = new ArrayList<>();
        filterCriteria.add(filtroDesembolso);
        filterCriteria.add(filtroID);
        filterCriteria.add(filtroNoReversado);
        var sortingCriteria = SortingCriteria.builder().field("valueDate").order("ASC").build();
        var searchDTO = SearchDTO.builder().filterCriteria(filterCriteria).sortingCriteria(sortingCriteria).build();
        return searchDTO;
    }

    public SearchDTO crearPaymentFilter(String id){
        var filtroDesembolso = FilterCriteria.builder()
                .field("type")
                .operator("EQUALS")
                .value("REPAYMENT")
                .build();
        var filtroID = FilterCriteria.builder()
                .field("parentAccountKey")
                .operator("EQUALS")
                .value(id)
                .build();
        var filtroNoReversado = FilterCriteria.builder()
                .field("adjustmentTransactionKey")
                .operator("EMPTY")
                .build();
        List<FilterCriteria> filterCriteria = new ArrayList<>();
        filterCriteria.add(filtroDesembolso);
        filterCriteria.add(filtroID);
        filterCriteria.add(filtroNoReversado);
        var sortingCriteria = SortingCriteria.builder().field("valueDate").order("ASC").build();
        var searchDTO = SearchDTO.builder().filterCriteria(filterCriteria).sortingCriteria(sortingCriteria).build();
        return searchDTO;
    }

    @Override
    public Flux<DisbursementResponseDTO> searchDisbursement(String id,String offset, String limit, String paginationDetails, String detailsLevel) {
        var searchDTO = crearDisbursementFilter(id);
        return webClientConfig.webClient().post()
                .uri(uriBuilder -> uriBuilder
                        .path("/loans/transactions:search")
                        .build())
                .body(Mono.just(searchDTO), SearchDTO.class)
                .header("Accept", headers.headers().getAccept())
                .header("Content-Type", headers.headers().getContentType())
                .retrieve().bodyToFlux(DisbursementResponseDTO.class).onErrorResume(e -> {
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
    public Flux<PaymentResponseDTO> searchPayment(String id,String offset, String limit, String paginationDetails, String detailsLevel) {
        var searchDTO = crearPaymentFilter(id);
        return webClientConfig.webClient().post()
                .uri(uriBuilder -> uriBuilder
                        .path("/loans/transactions:search")
                        .build())
                .body(Mono.just(searchDTO), SearchDTO.class)
                .header("Accept", headers.headers().getAccept())
                .header("Content-Type", headers.headers().getContentType())
                .retrieve().bodyToFlux(PaymentResponseDTO.class).onErrorResume(e -> {
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
    public Flux<AccLoanDTO> searchBlockLoanAccount(SearchDTO searchDTO) {
        return webClientConfig.webClient().post()
                .uri(uriBuilder -> uriBuilder
                        .path("/loans:search")
                        .build())
                .body(Mono.just(searchDTO), SearchDTO.class)
                .header("Accept", headers.headers().getAccept())
                .header("Content-Type", headers.headers().getContentType())
                .retrieve().bodyToFlux(AccLoanDTO.class).onErrorResume(e -> {
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
    public Mono<AccLoanDTO> getOperationByID(String id) {
        return webClientConfig.webClient().get()
                .uri(uriBuilder -> uriBuilder
                        .path("/loans/" + id)
                        .build())
                .header("Accept", headers.headers().getAccept())
                .retrieve().bodyToMono(AccLoanDTO.class).onErrorResume(e -> {
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
    public Mono<AccountPlanDTO> getAccountPlanByID(String id, String detailsLevel) {
        return webClientConfig.webClient().get()
                .uri(uriBuilder -> uriBuilder
                        .path("/loans/" + id + "/schedule")
                        .queryParam("detailsLevel", detailsLevel)
                        .build())
                .header("Accept", headers.headers().getAccept())
                .retrieve().bodyToMono(AccountPlanDTO.class).onErrorResume(e -> {
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
    public Mono<Object> doTotalDebtPayment(String id) {
        TotalDebtPaymentDTO totalDebtPayment = new TotalDebtPaymentDTO();
        return webClientConfig.webClient().post()
                .uri(uriBuilder -> uriBuilder
                        .path("/loans/" + id + ":payOff")
                        .build())
                .body(Mono.just(totalDebtPayment), TotalDebtPaymentDTO.class)
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
    public Mono<AccLoanDTO> doRefinanceLoan(RefinanceLoanDTO refinanceLoanDTO , String id) {
        return webClientConfig.webClient().post()
                .uri(uriBuilder -> uriBuilder
                        .path("/loans/" + id + ":refinance")
                        .build())
                .body(Mono.just(refinanceLoanDTO), RefinanceLoanDTO.class)
                .header("Accept", headers.headers().getAccept())
                .header("Content-Type", headers.headers().getContentType())
                .header("IdempotencyKey", headers.headers().getIdempotencyKey())
                .retrieve().bodyToMono(AccLoanDTO.class).onErrorResume(e -> {
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
    public Mono<AccLoanDTO> doRescheduleLoan(RescheduleLoanDTO rescheduleLoanDTO , String id) {
        return webClientConfig.webClient().post()
                .uri(uriBuilder -> uriBuilder
                        .path("/loans/" + id + ":reschedule")
                        .build())
                .body(Mono.just(rescheduleLoanDTO), RescheduleLoanDTO.class)
                .header("Accept", headers.headers().getAccept())
                .header("Content-Type", headers.headers().getContentType())
                .header("IdempotencyKey", headers.headers().getIdempotencyKey())
                .retrieve().bodyToMono(AccLoanDTO.class).onErrorResume(e -> {
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
