package com.mambu.back.model.dto.passive;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.io.Serializable;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BlockRequest implements Serializable {
    private long amount;
    private String externalReferenceId;
    private String notes;
    private String externalId;
    private String transactionChannelId;
    private String noteSeized;
}
