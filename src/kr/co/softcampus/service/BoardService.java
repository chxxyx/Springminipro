package kr.co.softcampus.service;

import java.io.File;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import kr.co.softcampus.beans.ContentBean;
import kr.co.softcampus.beans.UserBean;
import kr.co.softcampus.dao.BoardDao;

@Service
@PropertySource("/WEB-INF/properties/option.properties")
public class BoardService {
	
	@Value("${path.upload}")
	private String path_upload;
	
	@Autowired
	private BoardDao boardDao;
	
	@Resource(name = "loginUserBean")
	private UserBean loginUserBean;
	
	//파일 저장 메소드
	private String saveUplaodFile(MultipartFile upload_file) { //파일 이름이 중복될 수 있어서 현재시간 붙여서
		
		String file_name = System.currentTimeMillis() + "_" + upload_file.getOriginalFilename();
		
		try {
			
			upload_file.transferTo(new File(path_upload + "/" + file_name)); //주입받은 업로드
		} catch(Exception e) {
			
			e.printStackTrace();
		}
		
		return file_name;
		
	}
	
	public void addContentInfo(ContentBean writeContentBean) {
		
		MultipartFile upload_file = writeContentBean.getUpload_file(); //사용자가 요청한 파일
		
		if(upload_file.getSize() > 0) { //첨부파일이 있으면
			String file_name = saveUplaodFile(upload_file); // 현재시간 + 파일이름 불러주는 메소드 호출
			
			writeContentBean.setContent_file(file_name);
			
		}
		
		writeContentBean.setContent_writer_idx(loginUserBean.getUser_idx());
		
		boardDao.addContentInfo(writeContentBean);
	}
	
	public String getBoardInfoName(int board_info_idx) {
		
		return boardDao.getBoardInfoName(board_info_idx);
	}
	
	public List<ContentBean> getContentList(int board_info_idx) {
		
		return boardDao.getContentList(board_info_idx);
	}
	
	public ContentBean getContentInfo(int content_idx) {
		
		return boardDao.getContentInfo(content_idx);
	}

}
