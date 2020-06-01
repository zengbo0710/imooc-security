package com.imooc.dto;

import java.util.Date;

import javax.validation.constraints.Past;

import org.hibernate.validator.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonView;
import com.imooc.validator.MyConstraint;

import io.swagger.annotations.ApiModelProperty;

public class User {
	
	private String id;
	
	@MyConstraint(message = "这个是一个测试")
	private String username;
	
	@NotBlank(message = "密码不能为空")
	private String password;
	
	@Past
	@ApiModelProperty(value = "生日")
	private Date birthday;
	
	public interface UserSimpleView {};
	public interface UserDetailView extends UserSimpleView {};

	@JsonView(UserSimpleView.class)
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@JsonView(UserDetailView.class)
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@JsonView(UserSimpleView.class)
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@JsonView(UserSimpleView.class)
	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

}
