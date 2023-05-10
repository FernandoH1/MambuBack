package com.mambu.back.service;

import com.mambu.back.model.dto.active.ActiveInterestRateDTO;
import com.mambu.back.model.dto.passive.Account;
import com.mambu.back.model.dto.passive.Interest;
import com.mambu.back.model.dto.passive.InterestBody;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface InterestService {
    Flux<Account> searchInterest(Interest interest,String offset, String limit, String paginationDetails, String detailsLevel);

    Mono<Object> forceInterest(InterestBody interestBody, String id);

    Mono<Object> updateInterestLoanAccount(ActiveInterestRateDTO activeInterestRateDTO, String id);
}
