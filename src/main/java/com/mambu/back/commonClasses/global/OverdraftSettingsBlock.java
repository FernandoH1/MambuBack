package com.mambu.back.commonClasses.global;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.io.Serializable;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OverdraftSettingsBlock implements Serializable {
    private boolean allowOverdraft;
    private long overdraftLimit;
}
