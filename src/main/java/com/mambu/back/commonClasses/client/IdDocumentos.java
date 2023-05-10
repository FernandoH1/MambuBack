package com.mambu.back.commonClasses.client;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.io.Serializable;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class IdDocumentos implements Serializable {
    private String documentID;
    private String documentType;
}
