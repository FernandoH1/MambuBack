package com.mambu.back.controller;

import com.mambu.back.model.dto.passive.*;
import com.mambu.back.service.Impl.AccountServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/account")
public class AccountController {

    @Autowired
    AccountServiceImpl accountService;

    @PostMapping(value = "/create")
    @ResponseStatus(HttpStatus.CREATED)
    private Mono<Account> saveAccount(@RequestBody Account account) {
        return this.accountService.createAccount(account);
    }

    @PostMapping(value = "/blockFunds/{id}")
    @ResponseStatus(HttpStatus.OK)
    private Mono<BlockFunds> blockFunds(@RequestBody BlockFundsBody blockAccount, @PathVariable String id) {
        return this.accountService.blockFunds(blockAccount, id);
    }

    @PostMapping(value = "/seize/{id}")
    @ResponseStatus(HttpStatus.OK)
    private Mono<SeizeAccount> seizeAccount(@RequestBody SeizeAccountBody seizeAccount, @PathVariable String id) {
        return this.accountService.seizeAccount(seizeAccount, id);
    }

    @GetMapping(value = "/all/blockFunds/{id}")
    private Flux<BlockFunds> allBlocks(@PathVariable String id,
                                       @RequestParam String offset,
                                       @RequestParam String limit,
                                       @RequestParam String paginationDetails,
                                       @RequestParam String state) {
        return this.accountService.getAllBlocksAccountByID(id, offset, limit, paginationDetails, state);
    }

    @PostMapping(value = "/block/{id}")
    @ResponseStatus(HttpStatus.OK)
    private Mono<Account> blockAccount(@RequestBody BlockAccount blockAccount, @PathVariable String id) {
        return this.accountService.blockAccount(blockAccount, id);
    }

    @GetMapping(value = "/blockAccounts/{id}")
    private Mono<BlockAccountSearch> BlocksAccountsByID(@PathVariable String id, @RequestParam String detailsLevel) {
        return this.accountService.getBlockAccountByID(id, detailsLevel);
    }
}
