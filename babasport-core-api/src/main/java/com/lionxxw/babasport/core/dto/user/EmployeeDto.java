package com.lionxxw.babasport.core.dto.user;

import lombok.Data;

import java.io.Serializable;

/**
 * <p>Description: 员工 </p>
 *
 * @author wangxiang
 * @date 16/6/11 下午10:42
 * @version 1.0
 */
@Data
public class EmployeeDto implements Serializable {
	private String username;
	private String password;
	private String degree;
	private String email;
	private Integer gender;
	private String imgUrl;
	private String phone;
	private String realName;
	private String school;
	private Integer isDel;
}
