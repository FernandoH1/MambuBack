package com.mambu.back.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatusCode;

import java.io.Serializable;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ErrorDTO implements Serializable {
    private final HttpStatusCode status;
    private final String message;
    private final String path;
}
