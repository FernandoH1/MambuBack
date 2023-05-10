package com.mambu.back.commonClasses.product;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.io.Serializable;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MaturitySettings implements Serializable {
    private CreditArrangementSettings maturityPeriod;
    private String maturityPeriodUnit;
}
