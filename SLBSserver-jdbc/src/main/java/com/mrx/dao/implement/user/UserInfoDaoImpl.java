package com.mrx.dao.implement.user;

import java.sql.SQLException;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import com.mrx.dao.interfaces.user.UserInfoDao;
import com.mrx.entity.user.UserInfo;
import com.mrx.utils.dao.implement.AbstractDAO;

@Repository("userInfoDao")
public class UserInfoDaoImpl extends AbstractDAO<UserInfo>  implements UserInfoDao {
	
	 private static final Logger log = Logger .getLogger(UserInfoDaoImpl.class);  
	 
	 
	@Override
	public void add(UserInfo entity) throws SQLException {
		save(entity);
	}

	@Override
	public void updata(UserInfo entity) throws SQLException {
		merge(entity);
	}
	
	@Override
	public UserInfo login(String name, String password) {
		String sql = "SELECT * FROM userinfo WHERE name=? AND password=?";
		return getEntity(sql, name,password);  
	}


	@Override
	public List<UserInfo> seacrh(UserInfo entity) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public UserInfo findByName(String name) throws SQLException {
		String sql = "select * from userinfo where name=?";
		return getEntity(sql);
	}


	@Override
	public int isDouble(String name) {
		// TODO Auto-generated method stub
		return 0;
	}

}
