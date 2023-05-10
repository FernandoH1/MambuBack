package com.mambu.back.commonClasses.deposit;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.io.Serializable;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AccountBalances implements Serializable {
    private String accountID;
    private long availableBalance;
    private String cardType;
    private long creditLimit;
    private String currencyCode;
    private long totalBalance;
}
