package com.mambu.back.model.dto.active;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.io.Serializable;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ActiveInterestRateDTO implements Serializable {
    private long interestRate;
    private String notes;
    private String valueDate;
}
