package com.newlecture.web.dao.mybatis;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.newlecture.web.dao.NoticeDao;
import com.newlecture.web.dao.NoticeFileDao;
import com.newlecture.web.vo.Notice;

public class MyBatisNoticeDao implements NoticeDao {

	//SqlSessionFactory factory = new SqlNewlecSessionFactory().getSqlSessionFactory();
	
	@Autowired
	private SqlSession session;
	
	@Override
	public List<Notice> getNotices(int page, String field, String query) {
		

		//SqlSession session = factory.openSession(); 

/*		HashMap<String, Object> params = new HashMap<String, Object>();
		params.put("page", page);
		params.put("field", field);
		params.put("query", query);

		List<Notice> list = session.selectList("getNotices", params);
		
		session.close();

		return list;
*/		
		NoticeDao dao = session.getMapper(NoticeDao.class);
		NoticeFileDao filedao = session.getMapper(NoticeFileDao.class);
		
		List<Notice> list = dao.getNotices(page, field, query);
		for(Notice n : list)
			n.setFiles(filedao.getNoticeFilesOfNotice(n.getCode()));
		
		return list;
	}

	@Override
	public Notice getNotice(String code) {
		
		/*	SqlSession session = factory.openSession(); 
		
		NoticeDao dao = session.getMapper(NoticeDao.class);
		
		return dao.getNotices(code);*/
		
		//SqlSession session = factory.openSession();
		
		Notice notice = session.selectOne("com.newlecture.web.dao.NoticeDao.getNotice",code);

		//session.close();
		
		return notice;
		

	}

	@Override
	public List<Notice> getNotices(int page) {
		
		//SqlSession session = factory.openSession(); 
		return getNotices(page,"TITLE","");
	}

	@Override
	public List<Notice> getNotices() {
		
		//SqlSession session = factory.openSession(); 
		
		return getNotices(1,"TITLE","");
	}

	@Override
	public int addNotice(Notice notice) {
		
		//SqlSession session = factory.openSession(); // auto commit , false´Â ¼öµ¿
		int result = 0;
		
		NoticeDao dao = session.getMapper(NoticeDao.class);
		result = dao.addNotice(notice);
	
		return result;
	}

	@Override
	public String getLastCode() {
		
		//SqlSession session = factory.openSession(); 
		
		NoticeDao dao = session.getMapper(NoticeDao.class);
		
		return dao.getLastCode();
		
	}
}
