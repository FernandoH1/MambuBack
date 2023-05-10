package com.mambu.back.model.dto.active;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.mambu.back.commonClasses.transaction.FilterCriteria;
import com.mambu.back.commonClasses.transaction.SortingCriteria;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SearchDTO implements Serializable {
    private List<FilterCriteria> filterCriteria;
    private SortingCriteria sortingCriteria;
}
