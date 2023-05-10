package com.mambu.back.model.dto.active;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.mambu.back.model.dto.active.common.InterestSettings;
import com.mambu.back.model.dto.active.common.PenaltySettings;
import com.mambu.back.model.dto.active.common.ScheduleSettings;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class LoanAccountDTO implements Serializable {
    private String encodedKey;
    private String id;
    private String accountHolderKey;
    private String accountHolderType;
    private String accountState;
    private String accountSubState;
    private InterestSettings interestSettings;
    private long loanAmount;
    private String loanName;
    private String paymentMethod;
    private String productTypeKey;
    private ScheduleSettings scheduleSettings;
    private PenaltySettings penaltySettings;
}
