package com.mambu.back.model.dto.passive;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.mambu.back.commonClasses.account.AccruedAmounts;
import com.mambu.back.commonClasses.global.InterestSettingsBlock;
import com.mambu.back.commonClasses.global.InternalControls;
import com.mambu.back.commonClasses.global.OverdraftSettings;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.OffsetDateTime;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Account implements Serializable {
    private String encodedKey;
    private OffsetDateTime creationDate;
    private OffsetDateTime lastModifiedDate;
    private String id;
    private String name;
    private String notes;
    private String accountHolderType;
    private String accountHolderKey;
    private String accountState;
    private String productTypeKey;
    private String accountType;
    private OffsetDateTime approvedDate;
    private String currencyCode;
    private InternalControls internalControls;
    private OverdraftSettings overdraftSettings;
    private InterestSettingsBlock interestSettings;
    private InternalControls overdraftInterestSettings;
    private Map<String, Long> balances;
    private AccruedAmounts accruedAmounts;
}
