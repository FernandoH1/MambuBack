package com.mambu.back.model.dto.passive;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.mambu.back.commonClasses.client.Perzonalizado;
import lombok.Data;

import java.io.Serializable;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AccountClient implements Serializable {
    private String firstName;
    private String lastName;
    @JsonProperty("_personalizados")
    private Perzonalizado personalizados;
    private String productId;
    private String accountHolderKey;
    private String accountHolderType;
    private String name;
    private String notes;
    private String accountState;
    private String productTypeKey;

}
