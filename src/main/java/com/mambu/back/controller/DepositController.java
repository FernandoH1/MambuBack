package com.mambu.back.controller;

import com.mambu.back.model.dto.passive.Deposit;
import com.mambu.back.model.dto.passive.Transaction;
import com.mambu.back.service.Impl.DepositServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/deposit")
public class DepositController {

    @Autowired
    DepositServiceImpl depositService;

    @PostMapping(value = "/do/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    private Mono<Deposit> doDeposit(@RequestBody Deposit deposit, @PathVariable("id") String id) {
        return this.depositService.doDeposit(deposit, id);
    }

    @PostMapping(value = "/do/transaction")
    @ResponseStatus(HttpStatus.CREATED)
    private Flux<Deposit> doTransactionDeposit(@RequestBody Transaction transaction,
                                               @RequestParam String offset,
                                               @RequestParam String limit,
                                               @RequestParam String paginationDetails,
                                               @RequestParam String detailsLevel) {
        return this.depositService.doTransactionDeposit(transaction, offset, limit, paginationDetails, detailsLevel);
    }
}
