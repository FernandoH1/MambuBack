package com.mambu.back.model.dto.active.common;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.io.Serializable;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TransactionDetailsDTO implements Serializable {
    private String transactionChannelKey;
    private String transactionChannelId;
}
