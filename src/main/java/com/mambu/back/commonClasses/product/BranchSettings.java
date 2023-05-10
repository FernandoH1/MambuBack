package com.mambu.back.commonClasses.product;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.io.Serializable;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BranchSettings implements Serializable {
    private boolean forAllBranches;
    private Object[] availableProductBranches;
}
