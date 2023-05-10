package com.mambu.back.service;

import com.mambu.back.model.dto.passive.Deposit;
import com.mambu.back.model.dto.passive.Transaction;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface DepositService {
    Mono<Deposit> doDeposit(Deposit deposit, String id);
    Flux<Deposit> doTransactionDeposit(Transaction transaction, String offset, String limit, String paginationDetails, String detailsLevel);
}
