package com.mambu.back.model.dto.active.common;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.io.Serializable;
import java.time.OffsetDateTime;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AccLoanDTO implements Serializable {
    private String encodedKey;
    private String id;
    private String accountHolderType;
    private OffsetDateTime creationDate;
    private OffsetDateTime valueDate;
    private OffsetDateTime lastLockedDate;
    private String accountState;
    private String accountSubState;
    private String loanName;
    private DisbursementDetails disbursementDetails;
    private InterestSettings interestSettings;
    private ScheduleSettings scheduleSettings;

}
