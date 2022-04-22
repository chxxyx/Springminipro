package kr.co.softcampus.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;

import kr.co.softcampus.beans.UserBean;

public class CheckLoginInterceptor implements HandlerInterceptor{
	
	private UserBean loginUserBean; //자바는 주입이 안 돼서 생성자 필요함
	
	public CheckLoginInterceptor(UserBean loginUserBean) {
			this.loginUserBean = loginUserBean;
	}
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		
		if(loginUserBean.isUserLogin() == false) {
			
			String contextPath = request.getContextPath();
			response.sendRedirect(contextPath + "/user/not_login");
			
			return false;
		}
		
		return true;
	}

}
