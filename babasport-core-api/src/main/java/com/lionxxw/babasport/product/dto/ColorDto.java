package com.lionxxw.babasport.product.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class ColorDto implements Serializable{

    private Integer id;


    private String name;


    private Integer parentId;


    private Boolean isDisplay;
}