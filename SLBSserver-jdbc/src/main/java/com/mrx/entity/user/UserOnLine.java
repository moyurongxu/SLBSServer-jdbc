package com.mrx.entity.user;

import java.io.Serializable;


public class UserOnLine implements Serializable {

	/**
	 * 用于记录用户登录信息，并判断用户是否已经登录
	 */
	private static final long serialVersionUID = 1L;
	
	private String name;// 用户登录名
	private String loginTime;// 最新登录时间
	private int version = 0;// 登录次

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLoginTime() {
		return loginTime;
	}

	public void setLoginTime(String loginTime) {
		this.loginTime = loginTime;
	}

}
