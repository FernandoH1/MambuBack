package com.mambu.back.service;

import com.mambu.back.model.dto.active.ApproveLoanDTO;
import com.mambu.back.model.dto.active.BlockLoanAccountDTO;
import com.mambu.back.model.dto.active.BlockResponseLoanAccountDTO;
import com.mambu.back.model.dto.active.LoanAccountDTO;
import reactor.core.publisher.Mono;

public interface LoanAccountService {
    Mono<LoanAccountDTO> createLoanAccount(LoanAccountDTO loanAccountDTO);

    Mono<LoanAccountDTO> approveLoanAccount(String id);

    Mono<BlockResponseLoanAccountDTO> blockLoanAccount(BlockLoanAccountDTO blockLoanAccountDTO, String id);

    Mono<LoanAccountDTO> searchBlockByID(String id);
}
