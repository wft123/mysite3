package com.hanains.mysite.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hanains.mysite.vo.BoardVo;

@Repository
public class BoardDao {
	
	@Autowired
	private SqlSession sqlSession;
	
	public BoardDao(){
	}
	
	public void insert(BoardVo vo){
		sqlSession.insert("board.insert",vo);
	}
	
	public BoardVo getView(long no){
		BoardVo vo = sqlSession.selectOne("board.getView", no);
		return vo;
	}
	
	public void upCount(long no){
		sqlSession.update("board.upViewCount",no);
	}
	
	public void modify(BoardVo vo){
		sqlSession.update("board.modify",vo);
	}
	
	public void delete(long no){
		sqlSession.delete("board.delete",no);
	}

	public long getBoardSize(String kwd, String searchType){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("kwd", kwd);
		map.put("searchType", searchType);
		return sqlSession.selectOne("board.getBoardSize",map);
	}
	
	public long getMaxGroup(){
		return sqlSession.selectOne("board.getMaxGroup");
	}
	
	public long getMaxOrder(long group_no){
		return sqlSession.selectOne("board.getMaxOrder", group_no);
	}
	
	public List<BoardVo> getListPage(long pageNo, String kwd, String searchType, int pageSize){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("kwd", kwd);
		map.put("searchType", searchType);
		map.put("start", 1+(pageNo-1)*pageSize);
		map.put("end", pageNo*pageSize);
		
		List<BoardVo> list = sqlSession.selectList("board.getListPage",map);
		return list;
	}
	
}
