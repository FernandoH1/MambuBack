package com.mambu.back.model.dto.active;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.mambu.back.model.dto.active.common.TransactionDetailsDTO;
import lombok.Data;

import java.io.Serializable;
import java.time.OffsetDateTime;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PaymentResponseDTO implements Serializable {
    private String encodedKey;
    private String id;
    private OffsetDateTime creationDate;
    private OffsetDateTime valueDate;
    private String notes;
    private String parentAccountKey;
    private String type;
    private long amount;
    private TransactionDetailsDTO transactionDetails;
}
