package com.mrx.entity.user;

import java.io.Serializable;

import com.mrx.common.StringUtil;
import com.mrx.utils.entity.annotation.Column;
import com.mrx.utils.entity.annotation.Table;

@Table(table="userinfo")
public class UserInfo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Column(name="id",type=Integer.class,isPrimary = true,isAutoIncrement=true)
	protected int id=0;//用户id
	@Column(name="name",isPrimary = true)
	protected String name = StringUtil.FS_STR;//用户名
	@Column(name="password")
	protected String password = StringUtil.FS_STR;//用户密码
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
}
