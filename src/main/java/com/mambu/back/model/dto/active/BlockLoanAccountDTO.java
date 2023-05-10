package com.mambu.back.model.dto.active;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BlockLoanAccountDTO implements Serializable {
    private String[] lockedOperations;
    private String notes;
}
