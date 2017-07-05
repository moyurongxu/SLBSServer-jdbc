package com.mrx.service.implement.user;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mrx.common.DateFormart;
import com.mrx.common.StringUtil;
import com.mrx.dao.interfaces.user.UserInfoDao;
import com.mrx.dao.interfaces.user.UserOnLineDao;
import com.mrx.entity.user.UserInfo;
import com.mrx.entity.user.UserOnLine;
import com.mrx.service.interfaces.user.UserInfoService;

@Service("userInfoService") 
public class UserInfoServiceImpl implements UserInfoService{
	
	@Autowired  
    private UserInfoDao userInfoDao; 
	
	@Autowired  
    private UserOnLineDao userOnLineDao; 
	
	@Override
	public UserInfo getByName(String  name) throws SQLException {
		return userInfoDao.findByName(name);
	}

	@Override
	public List<UserInfo> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void add(UserInfo userInfo) throws SQLException {
		 userInfoDao.add(userInfo);
	}

	@Override
	public UserInfo login(String name, String password) {
		return userInfoDao.login(name, password);
	}

	@Override
	public void saveUserOnline(String name)  {
		UserOnLine userOnLine = userOnLineDao.findByName(name);
		String nowTime = DateFormart.convertToYYYY_MM_DD_HHMMSS(new Date());
		if(!StringUtil.isEmpty(userOnLine.getName())){
			//修改登陆时间
			userOnLine.setLoginTime(nowTime);
			userOnLine.setVersion(userOnLine.getVersion()+1);
			userOnLineDao.updataByName(userOnLine);
		}else{
			//第一次登陆
			userOnLine = new UserOnLine();
			userOnLine.setLoginTime(nowTime);
			userOnLine.setName(name);
			userOnLine.setVersion(1);
			userOnLineDao.add(userOnLine);
		}
		
	}

	@Override
	public void update(UserInfo userInfo) throws SQLException {
		userInfoDao.updata(userInfo);
	}

}
