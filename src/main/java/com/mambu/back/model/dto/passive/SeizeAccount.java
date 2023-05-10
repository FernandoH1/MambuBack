package com.mambu.back.model.dto.passive;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.mambu.back.commonClasses.deposit.*;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.time.OffsetDateTime;
import java.util.UUID;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SeizeAccount implements Serializable {
    private String encodedKey;
    private String id;
    private UUID externalID;
    private OffsetDateTime creationDate;
    private OffsetDateTime valueDate;
    private String notes;
    private String parentAccountKey;
    private String type;
    private long amount;
    private String currencyCode;
    private AffectedAmounts affectedAmounts;
    private Taxes taxes;
    private AccountBalances accountBalances;
    private String userKey;
    private String blockID;
    private Terms terms;
    private TransactionDetails transactionDetails;
    private Taxes transferDetails;
    private Object[] fees;
}
