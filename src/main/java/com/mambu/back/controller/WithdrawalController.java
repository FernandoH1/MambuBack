package com.mambu.back.controller;

import com.mambu.back.model.dto.passive.Transaction;
import com.mambu.back.model.dto.passive.Withdrawal;
import com.mambu.back.service.Impl.WithdrawalServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/withdrawal")
public class WithdrawalController {

    @Autowired
    WithdrawalServiceImpl withdrawalService;

    @PostMapping(value = "/do/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    private Mono<Withdrawal> doWithdrawal(@RequestBody Withdrawal withdrawal, @PathVariable("id") String id) {
        return this.withdrawalService.doWithdrawal(withdrawal, id);
    }

    @PostMapping(value = "/do/transaction")
    @ResponseStatus(HttpStatus.CREATED)
    private Flux<Withdrawal> doTransactionDeposit(@RequestBody Transaction transaction,
                                                  @RequestParam String offset,
                                                  @RequestParam String limit,
                                                  @RequestParam String paginationDetails,
                                                  @RequestParam String detailsLevel) {
        return this.withdrawalService.doTransactionWithdrawal(transaction, offset, limit, paginationDetails, detailsLevel);
    }
}
