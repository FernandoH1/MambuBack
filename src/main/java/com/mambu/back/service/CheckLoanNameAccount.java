package com.mambu.back.service;

import com.mambu.back.model.dto.active.ActiveInterestRateDTO;
import reactor.core.publisher.Mono;

public interface CheckLoanNameAccount {
    Mono<Object> checkLoanNameAccount(ActiveInterestRateDTO activeInterestRateDTO, String id);
}
