package com.mambu.back.model.dto.passive;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.mambu.back.commonClasses.account.AccruedAmounts;
import com.mambu.back.commonClasses.global.InterestSettingsBlock;
import com.mambu.back.commonClasses.global.InternalControls;
import com.mambu.back.commonClasses.global.OverdraftSettingsBlock;
import lombok.Data;

import java.io.Serializable;
import java.util.Map;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BlockAccountSearch implements Serializable {
    private String encodedKey;
    private String creationDate;
    private String lastModifiedDate;
    private String id;
    private String name;
    private String notes;
    private String accountHolderType;
    private String accountHolderKey;
    private String accountState;
    private String productTypeKey;
    private String accountType;
    private String approvedDate;
    private String activationDate;
    private String lockedDate;
    private String currencyCode;
    private InternalControls internalControls;
    private OverdraftSettingsBlock overdraftSettings;
    private InterestSettingsBlock interestSettings;
    private InternalControls overdraftInterestSettings;
    private Map<String, Long> balances;
    private AccruedAmounts accruedAmounts;
}
