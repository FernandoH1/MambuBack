package com.mambu.back.service.Impl;

import com.mambu.back.config.WebClientConfig;
import com.mambu.back.headers.GetHeaders;
import com.mambu.back.model.Client;
import com.mambu.back.model.dto.active.*;
import com.mambu.back.service.ActiveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class ActiveServiceImpl implements ActiveService {
    @Autowired
    private WebClientConfig webClientConfig;

    @Autowired
    private GetHeaders headers;

    @Autowired
    ClientServiceImpl clientService;
    @Autowired
    ProductServiceImpl productService;
    @Autowired
    LoanAccountServiceImpl loanAccountService;
    @Autowired
    TransactionServiceImpl transactionService;
    @Autowired
    DisbursementServiceImpl disbursementService;
    @Autowired
    ValdiatorServiceImpl valdiatorService;

    @Override
    public Mono<OnBoardingClientDTO> onBoardingClient(LoanAccountClientDTO onBoarding) {
        Client client1 = new Client();
        client1.setFirstName(onBoarding.getFirstName());
        client1.setLastName(onBoarding.getLastName());
        client1.setPersonalizados(onBoarding.getPersonalizados());
        return clientService.createClient(client1).flatMap(client2 ->
                productService.getProductLoanByID(onBoarding.getProductId(), "0", "10", "OFF", "FULL").flatMap(product1 -> {
                    var account = LoanAccountDTO.builder()
                            .accountHolderKey(client2.getEncodedKey())
                            .accountHolderType(onBoarding.getAccountHolderType())
                            .productTypeKey(product1.getEncodedKey())
                            .loanAmount(onBoarding.getLoanAmount())
                            .scheduleSettings(onBoarding.getScheduleSettings())
                            .penaltySettings(onBoarding.getPenaltySettings())
                            .interestSettings(onBoarding.getInterestSettings())
                            .build();
                    return loanAccountService.createLoanAccount(account).map(account1 ->
                            OnBoardingClientDTO.builder().cliente(client2).loanAccountDTO(account1).build()
                    );
                })
        );
    }

    @Override
    public Mono<LoanAccountDTO> blockLoanAccount(BlockLoanAccountDTO blockLoanAccountDTO, String id) {
        var blockAccDto = BlockLoanAccountDTO.builder()
                .notes(blockLoanAccountDTO.getNotes())
                .lockedOperations(blockLoanAccountDTO.getLockedOperations())
                .build();
        return loanAccountService.blockLoanAccount(blockAccDto, id).flatMap(data ->
                loanAccountService.searchBlockByID(id)
        );
    }

    @Override
    public Mono<OperationAppMovileDTO> consultOperationAppMovile(String id) {
        String detailsLevel = "FULL";

        return transactionService.getOperationByID(id).flatMap(data ->
                transactionService.getAccountPlanByID(id,detailsLevel).map(data2 ->{
                        var appMovileDTO = OperationAppMovileDTO.builder()
                                .accLoanDTO(data)
                                .accountPlanDTO(data2)
                                .build();
                        return appMovileDTO;
                })
        );
    }

    @Override
    public Mono<DisbursementResponseDTO> refinancingRequest(RefinanceLoanDTO refinanceLoanDTO, String id){
        return valdiatorService.doRefinanceLoanValidator(refinanceLoanDTO,id).flatMap(data ->
            disbursementService.doDisbursement(data.getId()).map(data2 -> {
                data2.setIdRefinanceAccount(data.getId());
                return data2;
            })
        );
    }





}
