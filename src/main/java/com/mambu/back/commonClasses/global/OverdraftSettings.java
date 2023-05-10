package com.mambu.back.commonClasses.global;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.io.Serializable;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OverdraftSettings implements Serializable {
    private boolean allowOverdraft;
    private long maxOverdraftLimit;
    private boolean allowTechnicalOverdraft;
}
