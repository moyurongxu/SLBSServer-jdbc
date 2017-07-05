package com.mrx.dao.interfaces.user;

import com.mrx.entity.user.UserOnLine;

public interface UserOnLineDao {
	
	public UserOnLine findByName(String name);
	
	public void updataByName(UserOnLine userOnLine);
	
	public void add(UserOnLine userOnLine);
	
}
