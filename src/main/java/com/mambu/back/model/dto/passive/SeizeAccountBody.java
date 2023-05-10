package com.mambu.back.model.dto.passive;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SeizeAccountBody implements Serializable {
    private long amount;
    private String blockId;
    private String externalId;
    private String notes;
    private String transactionChannelId;
}
