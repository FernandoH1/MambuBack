package com.mambu.back.commonClasses.deposit;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.io.Serializable;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Taxes implements Serializable {
    private long deferredTaxOnInterestAmount;
    private long taxOnFeesAmount;
    private long taxOnInterestAmount;
    private long taxOnInterestFromArrearsAmount;
    private long taxOnPaymentHolidaysInterest;
    private long taxOnPenaltyAmount;
    private long taxRate;
}
