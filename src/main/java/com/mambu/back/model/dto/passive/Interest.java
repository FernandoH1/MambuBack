package com.mambu.back.model.dto.passive;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.mambu.back.commonClasses.transaction.FilterCriteria;
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
public class Interest implements Serializable {
    private FilterCriteria[] filterCriteria;
}
