package com.mambu.back.service;

import com.mambu.back.model.dto.passive.BlockAccountSearch;
import com.mambu.back.model.dto.passive.OnBoardingClient;
import com.mambu.back.model.dto.passive.SeizeAccount;
import com.mambu.back.model.dto.passive.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface PassiveService {
    Mono<OnBoardingClient> onBoarding(AccountClient onBoarding);

    Mono<SeizeAccount> seizureAccounts(BlockRequest blockRequest, String id);

    Flux<Object> applyInterest(InterestApply interestApply, String offset, String limit, String paginationDetails, String detailsLevel);

    Mono<BlockAccountSearch> searchBlockAccount(BlockAccountBodyReq blockAccountBodyReq, String id, String limit);

    Flux<TransactionDTO> getLastTransactions(String id, String offset,String limit, String paginationDetails,String detailsLevel);
}
