package com.mambu.back.commonClasses.transaction;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SortingCriteria implements Serializable {
    private String field;
    private String order;
}
