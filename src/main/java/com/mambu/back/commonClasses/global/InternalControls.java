package com.mambu.back.commonClasses.global;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.mambu.back.commonClasses.product.CreditArrangementSettings;
import lombok.Data;

import java.io.Serializable;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class InternalControls implements Serializable {
    private CreditArrangementSettings openingBalance;
}
