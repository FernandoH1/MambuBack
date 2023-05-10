package com.mambu.back.model.dto.active.common;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.io.Serializable;
import java.time.OffsetDateTime;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class InstallmentDTO implements Serializable {
    private String encodedKey;
    private String parentAccountKey;
    private String number;
    private OffsetDateTime dueDate;
    private String state;
    private boolean isPaymentHoliday;
    private InterestDTO principal;
    private InterestDTO interest;
}
