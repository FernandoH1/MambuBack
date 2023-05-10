package com.mambu.back.service.Impl;

import com.mambu.back.config.WebClientConfig;
import com.mambu.back.headers.GetHeaders;
import com.mambu.back.model.ErrorDTO;
import com.mambu.back.model.dto.active.ActiveInterestRateDTO;
import com.mambu.back.service.CheckLoanNameAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class CheckLoanNameAccountImpl implements CheckLoanNameAccount {
    @Autowired
    private WebClientConfig webClientConfig;

    @Autowired
    private GetHeaders headers;

    @Autowired
    TransactionServiceImpl transactionService;

    @Autowired
    InterestServiceImpl interestService;

    @Override
    public Mono<Object> checkLoanNameAccount(ActiveInterestRateDTO activeInterestRateDTO, String id){
        return transactionService.getOperationByID(id).flatMap(data -> {
            if(data.getLoanName().equals("MicroCreditoColombia")){
                var error = ErrorDTO.builder()
                        .status(HttpStatus.UNAUTHORIZED)
                        .message("No se le puede cambiar la tasa de interes a este producto: (microcredcol)")
                        .path("/interest/update/"+id)
                        .build();
                return Mono.just(error);
            }
            return interestService.updateInterestLoanAccount(activeInterestRateDTO,id);
        });

    }
}
