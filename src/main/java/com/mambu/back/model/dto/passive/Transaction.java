package com.mambu.back.model.dto.passive;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.mambu.back.commonClasses.transaction.FilterCriteria;
import com.mambu.back.commonClasses.transaction.SortingCriteria;
import lombok.Data;

import java.io.Serializable;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Transaction implements Serializable {
    private FilterCriteria[] filterCriteria;
    private SortingCriteria sortingCriteria;
}
