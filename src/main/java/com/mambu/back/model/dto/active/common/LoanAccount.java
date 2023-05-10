package com.mambu.back.model.dto.active.common;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class LoanAccount implements Serializable {
    private String productTypeKey;
    private DisbursementDetails disbursementDetails;
    private InterestSettings interestSettings;
    private ScheduleSettings scheduleSettings;
}
