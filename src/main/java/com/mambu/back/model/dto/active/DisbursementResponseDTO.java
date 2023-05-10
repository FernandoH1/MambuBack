package com.mambu.back.model.dto.active;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.mambu.back.model.dto.active.common.TransactionDetailsDTO;
import lombok.Data;

import java.io.Serializable;
import java.time.OffsetDateTime;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DisbursementResponseDTO implements Serializable {
    private String encodedKey;
    private String id;
    private String idRefinanceAccount;
    private String creationDate;
    private String valueDate;
    private String notes;
    private String parentAccountKey;
    private String type;
    private long amount;
    private TransactionDetailsDTO transactionDetails;
}
