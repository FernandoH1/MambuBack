package com.mambu.back.service;

import com.mambu.back.model.dto.passive.Transaction;
import com.mambu.back.model.dto.passive.Withdrawal;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface WithdrawalService {
    Mono<Withdrawal> doWithdrawal(Withdrawal withdrawal, String id);

    Flux<Withdrawal> doTransactionWithdrawal(Transaction transaction, String offset, String limit, String paginationDetails, String detailsLevel);
}
