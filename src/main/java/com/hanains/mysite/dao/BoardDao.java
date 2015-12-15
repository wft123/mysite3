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
	static public final int PAGE_ROW = 10;
	
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

	public int getBoardSize(String kwd, String searchType){
		if(kwd!=null) return sqlSession.selectOne("board.getBoardSize", "%"+kwd+"%");
		else return sqlSession.selectOne("board.getBoardSize", "%");
	}
	
	public long getMaxGroup(){
		return sqlSession.selectOne("board.getMaxGroup");
	}
	
	public long getMaxOrder(long group_no){
		return sqlSession.selectOne("board.getMaxOrder", group_no);
	}
	
	public List<BoardVo> getListPage(int pageNo, String kwd, String searchType){
		Map<String, Object> map = new HashMap<String, Object>();
		if(kwd!=null) map.put("kwd", "%"+kwd+"%");
		else map.put("kwd", "%");
		map.put("start", 1+(pageNo-1)*PAGE_ROW);
		map.put("end", pageNo*PAGE_ROW);
		
		List<BoardVo> list = sqlSession.selectList("board.getListPage",map);
		return list;
	}
	
}
