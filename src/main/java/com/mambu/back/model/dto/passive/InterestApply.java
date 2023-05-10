package com.mambu.back.model.dto.passive;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.mambu.back.commonClasses.transaction.FilterCriteria;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class InterestApply implements Serializable {
    private FilterCriteria[] filterCriteria;
    private String notes;

}
