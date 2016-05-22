package com.lionxxw.babasport.core.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class ProductDto implements Serializable {

    private Integer id;

    private String no;

    private String name;

    private Double weight;

    private Integer isNew;

    private Integer isHot;

    private Integer isCommend;

    private Date createTime;

    private String createUserId;

    private Date checkTime;

    private String checkUserId;

    private Integer isShow;

    private Integer isDel;

    private Integer typeId;

    private Integer brandId;

    private String keywords;

    private Integer sales;

    private String description;

    private String packageList;

    private String feature;

    private String color;

    private String size;
}