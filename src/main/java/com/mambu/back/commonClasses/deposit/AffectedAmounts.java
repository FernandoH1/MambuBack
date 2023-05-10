package com.mambu.back.commonClasses.deposit;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.io.Serializable;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AffectedAmounts implements Serializable {
    private long fundsAmount;
    private long interestAmount;
    private long feesAmount;
    private long overdraftAmount;
    private long overdraftFeesAmount;
    private long overdraftInterestAmount;
    private long technicalOverdraftAmount;
    private long technicalOverdraftInterestAmount;
    private long fractionAmount;
}
