package com.lionxxw.babasport.core.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class SizeDto implements Serializable{

    private Integer id;


    private String name;


    private Boolean isDisplay;
}