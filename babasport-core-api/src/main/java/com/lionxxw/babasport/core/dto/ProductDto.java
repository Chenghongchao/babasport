package com.lionxxw.babasport.core.dto;

import com.lionxxw.babasport.core.service.ProductTypeService;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class ProductDto implements Serializable {

    private Integer id;

    private String no;

    private String name;

    private Double weight;

    private Boolean isNew;

    private Boolean isHot;

    private Boolean isCommend;

    private Date createTime;

    private String createUserId;

    private Date checkTime;

    private String checkUserId;

    private Boolean isShow;

    private Boolean isDel;

    private Integer typeId;

    private Integer brandId;

    private String keywords;

    private Integer sales;

    private String feature;

    private String color;

    private String size;

    private String description;

    private String packageList;

    private ProductImageDto image;
}