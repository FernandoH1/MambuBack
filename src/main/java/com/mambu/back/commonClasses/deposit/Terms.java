package com.mambu.back.commonClasses.deposit;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.mambu.back.commonClasses.global.InterestSettings;
import lombok.Data;

import java.io.Serializable;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Terms implements Serializable {
    private InterestSettings interestSettings;
    private Taxes overdraftInterestSettings;
    private Taxes overdraftSettings;
}
