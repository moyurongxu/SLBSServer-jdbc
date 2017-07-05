package com.mrx.dao.implement.user;

import java.sql.SQLException;

import org.springframework.stereotype.Repository;

import com.mrx.dao.interfaces.user.UserDao;
import com.mrx.entity.user.UserInfo;
import com.mrx.utils.dao.implement.AbstractDAO;

@Repository("userDao")
public class UserDaoImpl extends AbstractDAO<UserInfo> implements UserDao {

	@Override
	public UserInfo getUserInfo() throws SQLException {
		String sql = "select * from userinfo where name='20120999'";
		return find(sql);
	}

}
