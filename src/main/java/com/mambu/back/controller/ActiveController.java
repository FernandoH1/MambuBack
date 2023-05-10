package com.mambu.back.controller;

import com.mambu.back.model.dto.active.*;
import com.mambu.back.model.dto.passive.BlockAccountBodyReq;
import com.mambu.back.model.dto.passive.BlockAccountSearch;
import com.mambu.back.service.Impl.ActiveServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/active")
public class ActiveController {

    @Autowired
    ActiveServiceImpl activeService;

    @PostMapping(value = "/onBoarding")
    @ResponseStatus(HttpStatus.CREATED)
    private Mono<OnBoardingClientDTO> onBoradingClient(@RequestBody LoanAccountClientDTO onBoarding) {
        return activeService.onBoardingClient(onBoarding);
    }

    @PostMapping(value = "/block/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<LoanAccountDTO> blockLoanAccount(@RequestBody BlockLoanAccountDTO blockLoanAccountDTO,
                                                 @PathVariable String id) {
        return activeService.blockLoanAccount(blockLoanAccountDTO,id);
    }

    @PostMapping(value = "/appMovil/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<OperationAppMovileDTO> consultOperationAppMovile(@PathVariable String id) {
        return activeService.consultOperationAppMovile(id);
    }

    @PostMapping(value = "/refinancingRequest/{id}")
    @ResponseStatus(HttpStatus.OK)
    private Mono<DisbursementResponseDTO> refinancingRequest(@RequestBody RefinanceLoanDTO refinanceLoanDTO,
                                                             @PathVariable String id) {
        return this.activeService.refinancingRequest(refinanceLoanDTO,id);
    }



}
