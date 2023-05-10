package com.mambu.back.controller;

import com.mambu.back.model.dto.active.*;
import com.mambu.back.model.dto.active.common.AccLoanDTO;
import com.mambu.back.service.Impl.TransactionServiceImpl;
import com.mambu.back.service.Impl.ValdiatorServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/transaction")
public class TransactionController {

    @Autowired
    TransactionServiceImpl transactionService;

    @Autowired
    ValdiatorServiceImpl valdiatorService;

    @PostMapping(value = "/disbursement/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    private Flux<DisbursementResponseDTO> searchTransactionDisbursement(@PathVariable("id") String id,
                                                               @RequestParam String offset,
                                                               @RequestParam String limit,
                                                               @RequestParam String paginationDetails,
                                                               @RequestParam String detailsLevel) {
        return this.transactionService.searchDisbursement(id, offset, limit, paginationDetails, detailsLevel);
    }

    @PostMapping(value = "/payment/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    private Flux<PaymentResponseDTO> searchTransactionPayment(@PathVariable("id") String id,
                                                              @RequestParam String offset,
                                                              @RequestParam String limit,
                                                              @RequestParam String paginationDetails,
                                                              @RequestParam String detailsLevel) {
        return this.transactionService.searchPayment(id, offset, limit, paginationDetails, detailsLevel);
    }

    @PostMapping(value = "/block")
    @ResponseStatus(HttpStatus.CREATED)
    private Flux<AccLoanDTO> searchBlockLoanAccount(@RequestBody SearchDTO searchDTO) {
        return this.transactionService.searchBlockLoanAccount(searchDTO);
    }

    @GetMapping(value = "/operation/{id}")
    private Mono<AccLoanDTO> getOperationByID(@PathVariable String id) {
        return this.transactionService.getOperationByID(id);
    }

    @GetMapping(value = "/accountPlan/{id}")
    private Mono<AccountPlanDTO> getAccountPlanByID(@PathVariable String id, @RequestParam String detailsLevel) {
        return this.transactionService.getAccountPlanByID(id,detailsLevel);
    }

    @PostMapping(value = "/totalDebtPayment/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    private Mono<Object> doTotalDebtPayment(@PathVariable String id) {
        return this.transactionService.doTotalDebtPayment(id);
    }

    @PostMapping(value = "/refinanceLoan/{id}")
    @ResponseStatus(HttpStatus.OK)
    private Mono<AccLoanDTO> doRefinanceLoan(@RequestBody RefinanceLoanDTO refinanceLoanDTO,
                                            @PathVariable String id) {
        return this.valdiatorService.doRefinanceLoanValidator(refinanceLoanDTO,id);
    }

    @PostMapping(value = "/rescheduleLoan/{id}")
    @ResponseStatus(HttpStatus.OK)
    private Mono<AccLoanDTO> doRescheduleLoan(@RequestBody(required = false) RescheduleLoanDTO rescheduleLoanDTO,
                                             @PathVariable String id) {
        return this.valdiatorService.doRescheduleLoanValidator(rescheduleLoanDTO,id);
    }
}
