package kr.co.softcampus.interceptor;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;

import kr.co.softcampus.beans.BoardInfoBean;
import kr.co.softcampus.beans.UserBean;
import kr.co.softcampus.service.TopMenuService;

public class TopMenuInterceptor implements HandlerInterceptor {

	private TopMenuService topMenuService; //서비스 메소드 호출
	private UserBean loginUserBean;
	
	public TopMenuInterceptor(TopMenuService topMenuService, UserBean loginUserBean) {
		this.topMenuService = topMenuService;
		this.loginUserBean = loginUserBean;
		
	} //자바는 주입을 못 받으므로 생성자로 
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// TODO Auto-generated method stub
		
		List<BoardInfoBean> topMenuList = topMenuService.getTopMenuList(); 
		request.setAttribute("topMenuList", topMenuList); //탑메뉴리스트라는 이름으로 탑메뉴리스트 세팅
		request.setAttribute("loginUserBean", loginUserBean);
		
		return true; //다음단계로 나갈 수 있도록 반환값 트루
	}
	
	
	
	
}
