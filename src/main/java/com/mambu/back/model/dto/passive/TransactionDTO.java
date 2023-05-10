package com.mambu.back.model.dto.passive;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.mambu.back.commonClasses.deposit.*;
import lombok.Data;

import java.io.Serializable;
import java.time.OffsetDateTime;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TransactionDTO implements Serializable {
    private String encodedKey;
    private String id;
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
    private Terms terms;
    private TransactionDetails transaction;
    private Taxes transferDetails;
    private Object[] fees;
}
