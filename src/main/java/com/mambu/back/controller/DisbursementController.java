package com.mambu.back.controller;

import com.mambu.back.model.dto.active.ApproveLoanDTO;
import com.mambu.back.model.dto.active.DisbursementDTO;
import com.mambu.back.model.dto.active.DisbursementResponseDTO;
import com.mambu.back.model.dto.active.LoanAccountDTO;
import com.mambu.back.service.Impl.DisbursementServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/disbursement")
public class DisbursementController {
    @Autowired
    DisbursementServiceImpl disbursementService;

    @PostMapping(value = "/do/{id}")
    @ResponseStatus(HttpStatus.OK)
    private Mono<DisbursementResponseDTO> doDisbursement(@PathVariable("id") String id) {
        return this.disbursementService.doDisbursement(id);
    }
}
