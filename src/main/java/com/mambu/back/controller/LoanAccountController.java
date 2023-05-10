package com.mambu.back.controller;

import com.mambu.back.model.dto.active.ApproveLoanDTO;
import com.mambu.back.model.dto.active.BlockLoanAccountDTO;
import com.mambu.back.model.dto.active.BlockResponseLoanAccountDTO;
import com.mambu.back.model.dto.active.LoanAccountDTO;
import com.mambu.back.model.dto.active.common.AccLoanDTO;
import com.mambu.back.service.Impl.LoanAccountServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/loans")
public class LoanAccountController {
    @Autowired
    LoanAccountServiceImpl loanAccountService;

    @PostMapping(value = "/create")
    @ResponseStatus(HttpStatus.CREATED)
    private Mono<LoanAccountDTO> saveLoanAccount(@RequestBody LoanAccountDTO loanAccountDTO) {
        return this.loanAccountService.createLoanAccount(loanAccountDTO);
    }

    @PostMapping(value = "/approve/{id}")
    @ResponseStatus(HttpStatus.OK)
    private Mono<LoanAccountDTO> approveLoanAccount(@PathVariable("id") String id) {
        return this.loanAccountService.approveLoanAccount(id);
    }

    @PostMapping(value = "/block/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    private Mono<BlockResponseLoanAccountDTO> blockLoanAccount(@RequestBody BlockLoanAccountDTO blockLoanAccountDTO, @PathVariable("id") String id) {
        return this.loanAccountService.blockLoanAccount(blockLoanAccountDTO,id);
    }

    @GetMapping(value = "/searhBlock/{id}")
    private Mono<LoanAccountDTO> searchBlockByID(@PathVariable String id) {
        return this.loanAccountService.searchBlockByID(id);
    }

}
