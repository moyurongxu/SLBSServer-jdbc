package com.mrx.dao.interfaces.user;

import java.sql.SQLException;

import com.mrx.entity.user.UserInfo;

public interface UserDao {

	public UserInfo getUserInfo() throws SQLException;
}
