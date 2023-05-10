package com.mambu.back.service.Impl;

import com.mambu.back.config.WebClientConfig;
import com.mambu.back.headers.GetHeaders;
import com.mambu.back.model.dto.active.RefinanceLoanDTO;
import com.mambu.back.model.dto.active.RescheduleLoanDTO;
import com.mambu.back.model.dto.active.common.*;
import com.mambu.back.service.ValdiatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Mono;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;

@Service
public class ValdiatorServiceImpl implements ValdiatorService {
    @Autowired
    private WebClientConfig webClientConfig;
    @Autowired
    private GetHeaders headers;
    @Autowired
    ProductServiceImpl productService;
    @Autowired
    TransactionServiceImpl transactionService;

    private final String IDPRODUCTO = "microcredcol";
    private final String OFFSET = "0";
    private final String LIMIT = "10";
    private final String PAGINATIONDETAILS = "OFF";
    private final String DETAILSLEVEL = "FULL";

    private String futureDate() {
        OffsetDateTime date = OffsetDateTime.now(ZoneOffset.of("-05:00")).plusDays(15).minusSeconds(1);
        String dateNow = date.toString();
        return dateNow;
    }

    public Mono<RescheduleLoanDTO> bodyIsNullResh(RescheduleLoanDTO rescheduleLoanDTO) {
            var disbursement = DisbursementDetails.builder()
                    .firstRepaymentDate(futureDate())
                    .build();
            return productService.getProductLoanByID(IDPRODUCTO, OFFSET, LIMIT, PAGINATIONDETAILS, DETAILSLEVEL).map(productoLoanDTO -> {
                var interest = InterestSettings.builder()
                        .interestRate(productoLoanDTO.getInterestSettings().getIndexRateSettings().getInterestRate().getDefaultValue())
                        .build();
                var scheduleSetting = ScheduleSettings.builder().gracePeriod(0)
                        .repaymentInstallments(productoLoanDTO.getScheduleSettings().getNumInstallments().getDefaultValue())
                        .build();
                var laonAccount = LoanAccount.builder()
                        .productTypeKey(productoLoanDTO.getEncodedKey())
                        .disbursementDetails(disbursement)
                        .interestSettings(interest)
                        .scheduleSettings(scheduleSetting)
                        .build();

                var rescheduleLoanDTO1 = RescheduleLoanDTO.builder()
                        .loanAccount(laonAccount)
                        .build();
                return rescheduleLoanDTO1;
            });
    }

    public Mono<RefinanceLoanDTO> bodyIsNullRef(RefinanceLoanDTO refinanceLoanDTO) {
        var disbursement = DisbursementDetails.builder()
                .firstRepaymentDate(futureDate())
                .build();
        return productService.getProductLoanByID(IDPRODUCTO, OFFSET, LIMIT, PAGINATIONDETAILS, DETAILSLEVEL).map(productoLoanDTO -> {
            var interest = InterestSettings.builder()
                    .interestRate(productoLoanDTO.getInterestSettings().getIndexRateSettings().getInterestRate().getDefaultValue())
                    .build();
            var scheduleSetting = ScheduleSettings.builder().gracePeriod(0)
                    .repaymentInstallments(productoLoanDTO.getScheduleSettings().getNumInstallments().getDefaultValue())
                    .build();
            var laonAccount = LoanAccount.builder()
                    .productTypeKey(productoLoanDTO.getEncodedKey())
                    .disbursementDetails(disbursement)
                    .interestSettings(interest)
                    .scheduleSettings(scheduleSetting)
                    .build();
            var refinanceLoanDTO1 = RefinanceLoanDTO.builder()
                    .loanAccount(laonAccount)
                    .topUpAmount(refinanceLoanDTO.getTopUpAmount())
                    .build();
            return refinanceLoanDTO1;
        });
    }

    @Override
    public Mono<AccLoanDTO> doRescheduleLoanValidator(RescheduleLoanDTO rescheduleLoanDTO, String id) {
        if (rescheduleLoanDTO == null) {
            return webClientConfig.webClient().post()
                    .uri(uriBuilder -> uriBuilder
                            .path("/loans/" + id + ":reschedule")
                            .build())
                    .body(bodyIsNullResh(rescheduleLoanDTO), RescheduleLoanDTO.class)
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
        return transactionService.doRescheduleLoan(rescheduleLoanDTO,id);
    }

    @Override
    public Mono<AccLoanDTO> doRefinanceLoanValidator(RefinanceLoanDTO refinanceLoanDTO , String id) {
        if (refinanceLoanDTO.getLoanAccount() == null) {
            return webClientConfig.webClient().post()
                    .uri(uriBuilder -> uriBuilder
                            .path("/loans/" + id + ":refinance")
                            .build())
                    .body(bodyIsNullRef(refinanceLoanDTO), RefinanceLoanDTO.class)
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
        return transactionService.doRefinanceLoan(refinanceLoanDTO,id);
    }

}
