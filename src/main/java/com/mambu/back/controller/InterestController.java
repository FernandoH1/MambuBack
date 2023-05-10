package com.mambu.back.controller;

import com.mambu.back.model.dto.active.ActiveInterestRateDTO;
import com.mambu.back.model.dto.passive.Account;
import com.mambu.back.model.dto.passive.Interest;
import com.mambu.back.model.dto.passive.InterestBody;
import com.mambu.back.service.Impl.CheckLoanNameAccountImpl;
import com.mambu.back.service.Impl.InterestServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/interest")
public class InterestController {

    @Autowired
    InterestServiceImpl interestService;

    @Autowired
    CheckLoanNameAccountImpl checkLoanNameAccount;

    @PostMapping(value = "/search")
    @ResponseStatus(HttpStatus.OK)
    private Flux<Account> searchInterest(@RequestBody Interest interest,
                                         @RequestParam String offset,
                                         @RequestParam String limit,
                                         @RequestParam String paginationDetails,
                                         @RequestParam String detailsLevel) {
        return this.interestService.searchInterest(interest, offset, limit, paginationDetails, detailsLevel);
    }

    @PostMapping(value = "/force/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    private Mono<Object> forceInterest(@RequestBody InterestBody interest,
                                       @PathVariable String id) {
        return this.interestService.forceInterest(interest, id);
    }

    @PostMapping(value = "/update/{id}")
    public Mono<Object> updateInterestLoanAccount(@RequestBody ActiveInterestRateDTO activeInterestRateDTO, @PathVariable("id") String id){
        return this.checkLoanNameAccount.checkLoanNameAccount(activeInterestRateDTO,id);
    }


}
