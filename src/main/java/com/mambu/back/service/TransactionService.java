package com.mambu.back.service;

import com.mambu.back.model.dto.active.*;
import com.mambu.back.model.dto.active.common.AccLoanDTO;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface TransactionService {
    Flux<DisbursementResponseDTO> searchDisbursement(String id,String offset, String limit, String paginationDetails, String detailsLevel);

    Flux<PaymentResponseDTO> searchPayment(String id,String offset, String limit, String paginationDetails, String detailsLevel);

    Flux<AccLoanDTO> searchBlockLoanAccount(SearchDTO searchDTO);

    Mono<AccLoanDTO> getOperationByID(String id);

    Mono<AccountPlanDTO> getAccountPlanByID(String id, String detailsLevel);

    Mono<Object> doTotalDebtPayment(String id);

    Mono<AccLoanDTO> doRefinanceLoan(RefinanceLoanDTO refinanceLoanDTO , String id);

    Mono<AccLoanDTO> doRescheduleLoan(RescheduleLoanDTO rescheduleLoanDTO , String id);
}
