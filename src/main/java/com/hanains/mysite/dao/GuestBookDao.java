package com.hanains.mysite.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hanains.mysite.vo.GuestBookVo;

@Repository
public class GuestBookDao {
	
	@Autowired
	private SqlSession sqlSession;
	
	public List<GuestBookVo> getGuestBook(){
		List<GuestBookVo> list = sqlSession.selectList("guestbook.getGuestBook");
		return list;
	}
	
	public void add(GuestBookVo vo){
		sqlSession.insert("guestbook.add",vo);
	}
	
	public void delete(GuestBookVo vo){
		sqlSession.delete("guestbook.delete",vo);
	}
}
