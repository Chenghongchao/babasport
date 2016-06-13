package com.lionxxw.babasport.user.dto;

import com.lionxxw.common.constants.SexEnum;
import com.lionxxw.common.utils.ObjectUtils;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>Description: 购买者 </p>
 *
 * @author wangxiang
 * @date 16/6/11 下午10:41
 * @version 1.0
 */
public class BuyerDto implements Serializable {
	@Getter
	@Setter
	private String userName;
	@Getter
	@Setter
	private String password;
	@Getter
	private Integer gender;
	public void setGender(Integer gender) {
		this.gender = gender;
		if ( null != gender){
			setGenderStr(SexEnum.getSexByIndex(gender));
		}
	}

	@Getter
	@Setter
	private String genderStr;

	@Getter
	@Setter
	private String email;
	@Getter
	@Setter
	private String realName;
	@Getter
	@Setter
	private Date registerTime;
	@Getter
	@Setter
	private Integer province;
	@Getter
	@Setter
	private Integer city;
	@Getter
	@Setter
	private Integer town;
	@Getter
	@Setter
	private String addr;
	@Getter
	@Setter
	private Boolean isDel;
}
