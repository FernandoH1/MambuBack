package com.mambu.back.model.dto.active;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.mambu.back.model.dto.active.common.TransactionDetailsDTO;
import lombok.Data;

import java.io.Serializable;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TotalDebtPaymentDTO implements Serializable {
    private String externalId;
    private String notes;
    private TransactionDetailsDTO transactionDetails;
}
