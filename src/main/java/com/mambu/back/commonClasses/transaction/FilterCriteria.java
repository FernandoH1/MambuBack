package com.mambu.back.commonClasses.transaction;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class FilterCriteria implements Serializable {
    private String field;
    private String operator;
    private String secondValue;
    private String value;
    private List<String> values;
}
