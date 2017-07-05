package com.mrx.controller.user;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mrx.common.BaseResult;
import com.mrx.common.Constants;
import com.mrx.common.StringUtil;
import com.mrx.entity.user.UserInfo;
import com.mrx.service.interfaces.user.UserInfoService;
import com.mrx.service.interfaces.user.UserOnLineService;


@Controller
public class UserInfoController {

	private static final Logger log = Logger .getLogger(UserInfoController.class);  
	
	@Autowired
	private UserInfoService userInfoService;
	
	@Autowired
	private UserOnLineService userOnLineService;

	@RequestMapping(value = "user/login", method = RequestMethod.POST)
	@ResponseBody
	public BaseResult<String> userLogin(HttpSession session, @RequestBody UserInfo userInfo) {
		log.info(userInfo.getName()+ "/" + userInfo.getPassword());
		// 查询账号是否存在
		UserInfo u = userInfoService.login(userInfo.getName(), userInfo.getPassword());
		if (StringUtil.isNotEmpty(u)) {
			// 对比密码是否正确
			if (u.getPassword().equals(userInfo.getPassword())) {
				session.setAttribute(Constants.USER, u);
				//记录登陆
				userInfoService.saveUserOnline( u.getName() ) ;
				session.setAttribute(Constants.UserOnline ,userOnLineService.findByName(u.getName()));
				return BaseResult.successResult("登录成功");
			} else {
				return BaseResult.fail("密码错误");
			}
		} else {
			return BaseResult.fail("账号不存在");
		}
	}

	public BaseResult<UserInfo> findUserOfPage(
			@ModelAttribute(Constants.USER) UserInfo user, int startcom, int page) {
		return null;
	}

	@RequestMapping(value = "user/getuser", method = RequestMethod.POST)
	@ResponseBody
	public BaseResult<UserInfo> getUser(@ModelAttribute(Constants.USER) UserInfo user){
		if (!StringUtil.isNotEmpty(user)) {
			user.setPassword("");
			return BaseResult.successResult(user); 
		}
		return BaseResult.fail("请登录�?。�?");
	}
} 
