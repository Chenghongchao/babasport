package com.lionxxw.babasport.user.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

public class DeliveryAddressDto implements Serializable{

    @Getter
    @Setter
    private Integer id;

    @Getter
    @Setter
    private String buyer;

    @Getter
    @Setter
    private String province;

    @Getter
    @Setter
    private String city;

    @Getter
    @Setter
    private String town;

    @Getter
    @Setter
    private String provinceName;

    @Getter
    @Setter
    private String cityName;

    @Getter
    @Setter
    private String townName;

    @Getter
    @Setter
    private String consignee;

    @Getter
    @Setter
    private String address;

    @Getter
    @Setter
    private String mobile;

    @Getter
    @Setter
    private String telephone;

    @Getter
    @Setter
    private String email;

    @Getter
    @Setter
    private String postCode;

    @Getter
    @Setter
    private Date addTime;

    @Getter
    @Setter
    private Boolean isDefault;
}