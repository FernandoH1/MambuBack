package com.mambu.back.model.dto.active;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.mambu.back.model.dto.active.common.InstallmentDTO;
import lombok.Data;

import java.io.Serializable;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AccountPlanDTO implements Serializable {
    private InstallmentDTO[] installments;
}
