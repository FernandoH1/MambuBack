package com.mambu.back.model.dto.active;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.mambu.back.model.dto.active.common.AccLoanDTO;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OperationAppMovileDTO implements Serializable {
    private AccLoanDTO accLoanDTO;
    private AccountPlanDTO accountPlanDTO;
}
