package kr.co.softcampus.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;

import kr.co.softcampus.beans.ContentBean;

public interface BoardMapper {
															//오라클 가상 테이블						
	@SelectKey(statement = "select content_seq.nextval from dual", keyProperty = "content_idx", before = true, resultType = int.class) 																				//해당 작성 쿼리문보다 먼저 실행 
			//+1되는 반환값을 wirteContentBean 안에 있는 
			// "content_idx"라는 변수에 담겠다는 것 
	
	@Insert("insert into content_table(content_idx, content_subject, content_text, " +
			"content_file, content_writer_idx, content_board_idx, content_date) " +	//↓ 컬럼타입 지정 (널을 허용하는 컬럼 사용시에는 타입 명시를 해줘야 함)
			"values (#{content_idx}, #{content_subject}, #{content_text}, #{content_file, jdbcType=VARCHAR}, " +
			"#{content_writer_idx}, #{content_board_idx}, sysdate)")
	void addContentInfo(ContentBean writeContentBean); //selectkey 쿼리문 먼저 실행하고 실행

	
	@Select("select board_info_name " +
			"from board_info_table " +
			"where board_info_idx = #{board_info_idx}")
	String getBoardInfoName(int board_info_idx); //보드인포네임 하나라서 반환타입 = string
	
	
	@Select("select a1.content_idx, a1.content_subject, a2.user_name as content_writer_name, " +
			"to_char(a1.content_date, 'YYYY-MM-DD') as content_date " +
			"from content_table a1, user_table a2 " +
			"where a1.content_writer_idx = a2.user_idx " +
			"and a1.content_board_idx = #{board_info_idx} " +
			"order by a1.content_idx desc")
	List<ContentBean> getContentList(int board_info_idx); 
			
	@Select("select a2.user_name as content_writer_name, " +
			"	to_char(a1.content_date, 'YYYY-MM-DD') as content_date, " +
			"	a1.content_subject, a1.content_text, a1.content_file " +
			"from content_table a1, user_table a2 " +
			"where a1.content_writer_idx = a2.user_idx " +
			"	and content_idx = #{content_idx}")
	ContentBean getContentInfo(int content_idx); //read
	
	
	
}
