package com.mambu.back.commonClasses.global;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.mambu.back.commonClasses.product.InterestPaymentSettings;
import lombok.Data;

import java.io.Serializable;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class InterestSettingsBlock implements Serializable {
    private InterestRateSettingsBlock interestRateSettings;
    private InterestPaymentSettings interestPaymentSettings;
}
