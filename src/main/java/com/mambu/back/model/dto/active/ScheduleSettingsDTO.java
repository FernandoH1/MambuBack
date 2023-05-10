package com.mambu.back.model.dto.active;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.mambu.back.model.dto.active.common.NumInstallmentsDTO;
import lombok.Data;

import java.io.Serializable;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ScheduleSettingsDTO implements Serializable {
    private NumInstallmentsDTO numInstallments;
}
