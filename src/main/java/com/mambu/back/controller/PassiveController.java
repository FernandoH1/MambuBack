package com.mambu.back.controller;

import com.mambu.back.model.dto.passive.*;
import com.mambu.back.service.Impl.PassiveServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/passive")
public class PassiveController {

    @Autowired
    PassiveServiceImpl passiveService;

    @PostMapping(value = "/onBoarding")
    @ResponseStatus(HttpStatus.CREATED)
    private Mono<OnBoardingClient> onBorading(@RequestBody AccountClient onBoarding) {
        return passiveService.onBoarding(onBoarding);
    }

    @PostMapping(value = "/seize/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<SeizeAccount> seizureAccounts(@RequestBody BlockRequest blockRequest, @PathVariable String id) {
        return passiveService.seizureAccounts(blockRequest, id);
    }

    @PostMapping(value = "/interest")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Flux<Object> applyInterest(@RequestBody InterestApply interestApply,
                                      @RequestParam String offset,
                                      @RequestParam String limit,
                                      @RequestParam String paginationDetails,
                                      @RequestParam String detailsLevel) {
        return passiveService.applyInterest(interestApply, offset, limit, paginationDetails, detailsLevel);
    }

    @PostMapping(value = "/block/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<BlockAccountSearch> searchBlockAccount(@RequestBody BlockAccountBodyReq blockAccountBodyReq,
                                                       @PathVariable String id,
                                                       @RequestParam String detailsLevel) {
        return passiveService.searchBlockAccount(blockAccountBodyReq, id, detailsLevel);
    }

    @GetMapping(value = "/lastMoves/{id}")
    private Flux<TransactionDTO> allMoves(@PathVariable String id,
                                          @RequestParam String offset,
                                          @RequestParam String limit,
                                          @RequestParam String paginationDetails,
                                          @RequestParam String detailsLevel) {
        return passiveService.getLastTransactions(id, offset, limit, paginationDetails, detailsLevel);
    }


}
