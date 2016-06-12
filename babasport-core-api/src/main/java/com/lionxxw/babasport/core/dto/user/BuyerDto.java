package com.lionxxw.babasport.core.dto.user;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>Description: 购买者 </p>
 *
 * @author wangxiang
 * @date 16/6/11 下午10:41
 * @version 1.0
 */
@Data
public class BuyerDto implements Serializable {
	private String username;
	private String password;
	private Gender gender;
	private String email;
	private String realName;
	private Date registerTime;
	private String province;
	private String city;
	private String town;
	private String addr;
	private Integer isDel;
	
	public enum Gender {

		MAN{
			public String getName(){return "男";}
		},
		WOMAN{
			public String getName(){return "女";}
		},
		SECRECY{
			public String getName(){return "保密";}
		};
		
		public abstract String getName();
	}
}
