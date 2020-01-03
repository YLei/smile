package com.emx.platform.vo;

import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="登录用户对象",description="登录用户对象")

public class LoginVo {
	//用户名
	@ApiModelProperty(value="用户名",name="username",example="admin")
	@NotNull private String username;
	//密码
	@ApiModelProperty(value="密码",name="password",example="123456")
	@NotNull private String password;
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
}
