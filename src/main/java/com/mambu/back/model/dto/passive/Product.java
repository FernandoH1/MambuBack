package com.mambu.back.model.dto.passive;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.mambu.back.commonClasses.global.InterestSettings;
import com.mambu.back.commonClasses.global.InternalControls;
import com.mambu.back.commonClasses.global.OverdraftSettings;
import com.mambu.back.commonClasses.product.*;
import lombok.Data;

import java.io.Serializable;
import java.time.OffsetDateTime;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Product implements Serializable {
    private String encodedKey;
    private String id;
    private OffsetDateTime creationDate;
    private OffsetDateTime lastModifiedDate;
    private String name;
    private String notes;
    private String type;
    private String category;
    private String state;
    private NewAccountSettings newAccountSettings;
    private InterestSettings interestSettings;
    private CreditArrangementSettings overdraftInterestSettings;
    private OverdraftSettings overdraftSettings;
    private FeesSettings feesSettings;
    private AccountingSettings accountingSettings;
    private InternalControls internalControls;
    private MaturitySettings maturitySettings;
    private CreditArrangementSettings creditArrangementSettings;
    private AvailabilitySettings availabilitySettings;
    private OffsetSettings offsetSettings;
    private TaxSettings taxSettings;
    private CurrencySettings currencySettings;
    private Object[] templates;
}
