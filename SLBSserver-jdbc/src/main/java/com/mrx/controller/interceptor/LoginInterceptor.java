package com.mrx.controller.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.mrx.common.Constants;
import com.mrx.entity.user.UserInfo;
import com.mrx.entity.user.UserOnLine;
import com.mrx.service.interfaces.user.UserOnLineService;





@SessionAttributes(Constants.USER)
public class LoginInterceptor extends HandlerInterceptorAdapter {
	@Autowired
	private UserOnLineService userOnLineService;

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
	
			request.setCharacterEncoding("UTF8");
			response.setCharacterEncoding("UTF8");
			response.setContentType("text/html;charset=UTF-8");
	
			// 后台session控制
			String[] noFilters = new String[] { "/user/login", "/user/logout",
					"login" };
	
			String uri = request.getRequestURI();
			boolean beFilter = true;
			// 查看请求是否是登录或者�?�?
			for (String s : noFilters) {
				if (uri.lastIndexOf(s) != -1) {
					beFilter = false;
					break;
				}
			}
			System.out.println("url=" + uri);
			UserInfo user = (UserInfo) request.getSession().getAttribute(Constants.USER);
			if (beFilter) {
				if (null == user) {
					System.out.println("未登录");
					// 如果是ajax请求响应头会有，x-requested-with�?
					if (request.getHeader("x-requested-with") != null && request.getHeader("x-requested-with").equalsIgnoreCase("XMLHttpRequest")) {  
	                    response.setHeader("sessionstatus", "timeout");// 在响应头设置session状�?  
	                    return false;  
	                }
					request.getRequestDispatcher("/logins.html").forward(request,response);
					return false;
				} else {
					// 查询本账户的�?��登录时记录在session中的登录次数
					UserOnLine userOnlin = (UserOnLine) request.getSession().getAttribute(Constants.UserOnline);
					UserInfo u = (UserInfo) request.getSession().getAttribute(Constants.USER);
					// 查询数据库中现在的登录次�?
					int version = userOnLineService.findByName(u.getName()).getVersion(); 
					if (version != userOnlin.getVersion()) {
						System.out.println("已经登录超时");
						// 强制session超时
						request.getSession().invalidate();
						// 如果是ajax请求响应头会有，x-requested-with�?
						if (request.getHeader("x-requested-with") != null   && request.getHeader("x-requested-with").equalsIgnoreCase("XMLHttpRequest"))  {  
	                        response.setHeader("sessionstatus", "repeatlogin");// 在响应头设置session状�?  
	                        return false;  
	                    } 
						request.getRequestDispatcher("/logins.html").forward(request,response);
						return false;
					}
				}
			}
			return super.preHandle(request, response, handler);
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub
		super.postHandle(request, response, handler, modelAndView);
	}

	
	
}
