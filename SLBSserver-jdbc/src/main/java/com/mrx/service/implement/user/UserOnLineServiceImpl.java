package com.mrx.service.implement.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mrx.dao.interfaces.user.UserOnLineDao;
import com.mrx.entity.user.UserOnLine;
import com.mrx.service.interfaces.user.UserOnLineService;

@Service("userOnLineService")
public class UserOnLineServiceImpl implements UserOnLineService {

	@Autowired
	private  UserOnLineDao userOnLineDao; 
	
	@Override
	public UserOnLine findByName(String name) {
		
		return userOnLineDao.findByName(name);
	}

}
