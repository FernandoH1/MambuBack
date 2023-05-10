package com.mambu.back.commonClasses.global;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class InterestRateSettingsBlock implements Serializable {
    private String encodedKey;
    private long interestRate;
    private String interestChargeFrequency;
    private long interestChargeFrequencyCount;
    private List<Object> interestRateTiers;
    private String interestRateTerms;
    private String interestRateSource;
}
