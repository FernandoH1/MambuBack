package com.mambu.back.commonClasses.deposit;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.io.Serializable;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TransactionDetails implements Serializable {
    private String transactionChannelKey;
    private String transactionChannelID;
}
