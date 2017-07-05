package test;
import java.sql.SQLException;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.mrx.dao.interfaces.user.UserDao;
import com.mrx.entity.user.UserInfo;
import com.mrx.service.interfaces.user.UserInfoService;


@RunWith(SpringJUnit4ClassRunner.class)  
@ContextConfiguration(locations = { "classpath:applicationContext.xml" })
public class TestUserInfoService {
	
	 private static final Logger LOGGER = Logger .getLogger(TestUserInfoService.class);  
	    @Autowired  
	    private UserInfoService userInfoService;
	    @Autowired
	    private UserDao userDao;
//	    @Test  
	    public void testSave() throws SQLException {  
	        UserInfo userInfo = new UserInfo();  
	        userInfo.setName("20120998");
	        userInfo.setPassword("1234567890");
	        userInfoService.add(userInfo);  
	    }
	    
//	    @Test  
	    public void getEntity(){
	    	
	    	UserInfo user = null ;
	    	try {
				 user = userDao.getUserInfo();
			} catch (SQLException e) {
				e.printStackTrace();
			}
	    	System.out.println(user.getName());
	    }
	    
	    
	    @Test  
	    public void getEntitys(){
	    	
	    	UserInfo user = null ;
	    	try {
	    		user = userInfoService.getByName("20120999");
	    	} catch (SQLException e) {
	    		e.printStackTrace();
	    	}
	    	System.out.println(user.getName());
	    }
	    
}
