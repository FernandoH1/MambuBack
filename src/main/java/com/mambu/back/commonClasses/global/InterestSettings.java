package com.mambu.back.commonClasses.global;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.mambu.back.commonClasses.product.InterestPaymentSettings;
import com.mambu.back.commonClasses.product.InterestRateSettings;
import lombok.Data;

import java.io.Serializable;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class InterestSettings implements Serializable {
    private boolean interestPaidIntoAccount;
    private InterestRateSettings interestRateSettings;
    private String interestCalculationBalance;
    private InterestPaymentSettings interestPaymentSettings;
    private boolean collectInterestWhenLocked;
    private String daysInYear;
}
