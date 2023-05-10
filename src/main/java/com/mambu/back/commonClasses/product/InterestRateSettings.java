package com.mambu.back.commonClasses.product;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.io.Serializable;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class InterestRateSettings implements Serializable {
    private InterestRate interestRate;
    private String interestRateSource;
    private String interestRateTerms;
    private String interestChargeFrequency;
    private long interestChargeFrequencyCount;
    private String encodedKey;
    private boolean accrueInterestAfterMaturity;
    private Object[] interestRateTiers;
    private boolean allowNegativeInterestRate;
}
