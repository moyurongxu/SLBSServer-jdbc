package com.mrx.dao.implement.user;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import com.mrx.dao.interfaces.user.UserOnLineDao;
import com.mrx.entity.user.UserOnLine;
import com.mrx.utils.dao.implement.AbstractDAO;

@Repository("userOnLineDao")
public class UserOnLineDaoImpl extends AbstractDAO<UserOnLine> implements UserOnLineDao {
	
	 private static final Logger log = Logger .getLogger(UserOnLineDaoImpl.class);
	 
	 
	@Override
	public UserOnLine findByName(String name) {
		String sql = "select * from useronline where name = ?";
		log.info(sql+" " +name);
        return getEntity(sql, name);
	}

	@Override
	public void updataByName(UserOnLine userOnLine) {
		merge(userOnLine);
	}

	@Override
	public void add(UserOnLine userOnLine) {
		save(userOnLine);
	}

}
