package com.mambu.back.commonClasses.client;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Perzonalizado implements Serializable {
    @JsonProperty("External_ID")
    private String externalID;
}
