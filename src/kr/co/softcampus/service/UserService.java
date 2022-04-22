package kr.co.softcampus.service;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.softcampus.beans.UserBean;
import kr.co.softcampus.dao.UserDao;

@Service
public class UserService {

	@Autowired
	private UserDao userDao;
	
	@Resource(name = "loginUserBean") //성공했을 때 데이터에 담으려면 주입받아야 함
	private UserBean loginUserBean;
	
	public boolean checkUserIdExist(String user_id) {
		
		String user_name = userDao.checkUserIdExist(user_id);
		
		if(user_name == null) {
			return true;
		} else {
			return false;
		}
		
	} 
	
	
	public void addUserInfo(UserBean joinUserBean) {
		
		userDao.addUserInfo(joinUserBean);
	}
	
	
	public void getLoginUserInfo(UserBean tempLoginUserBean) {
		
		UserBean tempLoginUserBean2 = userDao.getLoginUserInfo(tempLoginUserBean); //dao에 있는 정보 빈을 넘김
		
		if(tempLoginUserBean2 != null) { //로그인 성공 처리
			loginUserBean.setUser_idx(tempLoginUserBean2.getUser_idx());
			loginUserBean.setUser_name(tempLoginUserBean2.getUser_name());
			loginUserBean.setUserLogin(true);
		}
	}
	
								//컨트롤러에서 주입받은 객체의 주소값
	public void getModifyUserInfo(UserBean modifyUserBean) {
		UserBean tempModifyUserBean =  userDao.getModifyUserInfo(loginUserBean.getUser_idx()); 
											//dao메소드 
		modifyUserBean.setUser_id(tempModifyUserBean.getUser_id());
		modifyUserBean.setUser_name(tempModifyUserBean.getUser_name());
		modifyUserBean.setUser_idx(loginUserBean.getUser_idx());
		
	}
	
	public void modifyUserInfo(UserBean modifyUserBean) {
	
		modifyUserBean.setUser_idx(loginUserBean.getUser_idx()); // 로그인한 사용자의 인덱스 번호 가져옴
			
		userDao.modifyUserInfo(modifyUserBean);
	}


}
