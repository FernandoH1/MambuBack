package com.mambu.back.commonClasses.account;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.io.Serializable;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class InterestSettingsAccount implements Serializable {
    private long interestRate;
    private long interestChargeFrequencyCount;
    private boolean accrueInterestAfterMaturity;
    private boolean allowNegativeInterestRate;
}
