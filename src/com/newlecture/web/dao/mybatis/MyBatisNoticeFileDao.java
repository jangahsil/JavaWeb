package com.newlecture.web.dao.mybatis;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.newlecture.web.dao.NoticeDao;
import com.newlecture.web.dao.NoticeFileDao;
import com.newlecture.web.vo.Notice;
import com.newlecture.web.vo.NoticeFile;

public class MyBatisNoticeFileDao implements NoticeFileDao {
	
	//SqlSessionFactory factory = new SqlNewlecSessionFactory().getSqlSessionFactory();

	@Autowired
	private SqlSession session;
	
	@Override
	public int addNoticeFile(NoticeFile noticeFile) {
			
			//SqlSession session = factory.openSession(); // auto commit , false는 수동
			int result = 0;
			
			try {
				NoticeFileDao dao = session.getMapper(NoticeFileDao.class);
				result = dao.addNoticeFile(noticeFile);
				session.commit();
				
			} finally {
				session.rollback();
				session.close();
			}
			return result;
	}

	@Override
	public List<NoticeFile> getNoticeFilesOfNotice(String code) {
		
		//SqlSession session = factory.openSession(); 
		
		//NoticeFileDao dao = (NoticeFileDao)session.getMapper(NoticeFileDao.class);

		List<NoticeFile> list = session.selectList("getNoticeFilesOfNotice", code);
		
		session.close();
		
		return list;
		
		
		
/*		List<NoticeFile> list = new ArrayList<NoticeFile>();
		NoticeFile file = new NoticeFile();
		file.setName("파일1");
		list.add(file);
		
		file = new NoticeFile();
		file.setName("파일2");
		list.add(file);
	
		return list;*/
	}

}
