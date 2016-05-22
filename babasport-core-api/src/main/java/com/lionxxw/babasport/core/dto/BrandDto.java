package com.lionxxw.babasport.core.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class BrandDto implements Serializable {

    private Integer id;

    private String name;

    private String description;

    private String imgUrl;

    private Integer sort;

    private Integer isDisplay;
}