package com.mambu.back.commonClasses.account;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.io.Serializable;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AccruedAmounts implements Serializable {
    private long interestAccrued;
    private long overdraftInterestAccrued;
    private long technicalOverdraftInterestAccrued;
    private long negativeInterestAccrued;
}
