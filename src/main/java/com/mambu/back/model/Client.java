package com.mambu.back.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.mambu.back.commonClasses.client.Perzonalizado;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.OffsetDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Client implements Serializable {
    private String encodedKey;
    private String id;
    private OffsetDateTime creationDate;
    private OffsetDateTime lastModifiedDate;
    private OffsetDateTime approvedDate;
    @NotBlank(message = "FirstName is Required")
    private String firstName;
    @NotBlank(message = "LastName is Required")
    private String lastName;
    @NotBlank(message = "personalizados.External_ID is Required")
    @JsonProperty("_personalizados")
    private Perzonalizado personalizados;

}
