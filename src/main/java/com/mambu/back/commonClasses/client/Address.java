package com.mambu.back.commonClasses.client;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.io.Serializable;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Address implements Serializable {
    private String city;
    private String country;
    private String encodedKey;
    private Integer indexInList;
    private Float latitude;
    private String line1;
    private String line2;
    private Float longitude;
    private String parentKey;
    private String postcode;
    private String region;

}
