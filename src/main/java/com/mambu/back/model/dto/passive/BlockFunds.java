package com.mambu.back.model.dto.passive;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.mambu.back.enums.State;
import lombok.Data;

import java.io.Serializable;
import java.time.OffsetDateTime;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BlockFunds implements Serializable {
    private long amount;
    private String externalReferenceId;
    private String accountKey;
    private State state;
    private long seizedAmount;
    private OffsetDateTime creationDate;
    private OffsetDateTime lastModifiedDate;
    private String notes;
}
