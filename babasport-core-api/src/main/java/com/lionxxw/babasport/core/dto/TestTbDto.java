package com.lionxxw.babasport.core.dto;

import java.io.Serializable;
import java.util.Date;

/**
 * 测试JavaBean
 *
 */
public class TestTbDto implements Serializable{
	
	private Integer id;
	private String name;
	private Date birthday;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
}
