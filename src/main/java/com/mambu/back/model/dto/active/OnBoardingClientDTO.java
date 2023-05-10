package com.mambu.back.model.dto.active;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.mambu.back.model.Client;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OnBoardingClientDTO implements Serializable {
    private Client cliente;
    private LoanAccountDTO loanAccountDTO;
}
