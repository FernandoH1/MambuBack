package com.mambu.back.commonClasses.product;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.io.Serializable;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AccountingSettings implements Serializable {
    private String accountingMethod;
    private String interestAccruedAccountingMethod;
    private Object[] accountingRules;
}
