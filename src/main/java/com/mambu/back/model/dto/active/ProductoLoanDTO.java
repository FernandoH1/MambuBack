package com.mambu.back.model.dto.active;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.mambu.back.model.dto.active.common.InterestSettingsDTO;
import com.mambu.back.model.dto.active.common.PenaltySettingsDTO;
import lombok.Data;

import java.io.Serializable;


@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProductoLoanDTO implements Serializable {
    private String encodedKey;
    private InterestSettingsDTO interestSettings;
    private PenaltySettingsDTO penaltySettings;
    private ScheduleSettingsDTO scheduleSettings;
}
