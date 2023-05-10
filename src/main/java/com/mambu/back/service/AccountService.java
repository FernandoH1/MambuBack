package com.mambu.back.service;

import com.mambu.back.model.dto.passive.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface AccountService {
    Mono<Account> createAccount(Account account);

    Mono<BlockFunds> blockFunds(BlockFundsBody blockAccount, String id);

    Mono<SeizeAccount> seizeAccount(SeizeAccountBody seizeAccount, String id);

    Flux<BlockFunds> getAllBlocksAccountByID(String id, String offset, String limit, String paginationDetails, String state);

    Mono<Account> blockAccount(BlockAccount blockAccount, String id);

    Mono<BlockAccountSearch> getBlockAccountByID(String id, String detailsLevel);
}
