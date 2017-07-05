package com.mrx.service.interfaces.user;

import java.sql.SQLException;
import java.util.List;

import com.mrx.entity.user.UserInfo;

public interface UserInfoService {

	public UserInfo getByName(String  name) throws SQLException;  
  
	public List<UserInfo> findAll();  
  
	public void add(UserInfo userInfo)throws SQLException; 
    
	public void update(UserInfo userInfo)throws SQLException; 
	
    public UserInfo login(String name, String password) ;
    
    public void saveUserOnline(String name);
}
