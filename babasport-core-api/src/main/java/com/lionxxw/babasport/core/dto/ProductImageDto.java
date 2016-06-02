package com.lionxxw.babasport.core.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class ProductImageDto implements Serializable {

    private Integer id;

    private Integer productId;

    private String url;

    private Boolean isDef;
}