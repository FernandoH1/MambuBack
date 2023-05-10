package com.mambu.back.service;

import com.mambu.back.model.dto.active.RefinanceLoanDTO;
import com.mambu.back.model.dto.active.RescheduleLoanDTO;
import com.mambu.back.model.dto.active.common.AccLoanDTO;
import reactor.core.publisher.Mono;

public interface ValdiatorService {
    Mono<AccLoanDTO> doRescheduleLoanValidator(RescheduleLoanDTO rescheduleLoanDTO, String id);

    Mono<AccLoanDTO> doRefinanceLoanValidator(RefinanceLoanDTO refinanceLoanDTO , String id);
}
