package com.lionxxw.babasport.core.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * <p>Description: 地址 </p>
 *
 * @author wangxiang
 * @date 16/6/11 下午10:41
 * @version 1.0
 */
@Data
public class AddrDto implements Serializable {

	private Integer id;
	private String buyerId;
	private String name;
	private String city;
	private String addr;
	private String phone;
	private Integer isDef;
}
