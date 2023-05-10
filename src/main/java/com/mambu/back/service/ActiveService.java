package com.mambu.back.service;

import com.mambu.back.model.dto.active.*;

import reactor.core.publisher.Mono;

public interface ActiveService {
    Mono<OnBoardingClientDTO> onBoardingClient(LoanAccountClientDTO onBoarding);

    Mono<LoanAccountDTO> blockLoanAccount(BlockLoanAccountDTO blockLoanAccountDTO, String id);

    Mono<OperationAppMovileDTO> consultOperationAppMovile(String id);

    Mono<DisbursementResponseDTO> refinancingRequest(RefinanceLoanDTO refinanceLoanDTO, String id);
}
