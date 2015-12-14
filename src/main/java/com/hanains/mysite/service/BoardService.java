package com.hanains.mysite.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Service;

import com.hanains.mysite.dao.BoardDao;
import com.hanains.mysite.vo.BoardVo;
import com.hanains.mysite.vo.UserVo;

@Service
public class BoardService {
	
 	@Autowired
	private BoardDao dao;
	
	public List<BoardVo> getListPage(int page, String kwd) {
		return dao.getListPage(page, kwd);
	}

	public int getBoardSize(String kwd) {
		return dao.getBoardSize(kwd);
	}

	public int getPageSize() {
		return dao.PAGE_ROW;
	}

	public BoardVo getView(long no) {
		return dao.getView(no);
	}

	public void upCount(long no) {
		dao.upCount(no);
	}

	public void delete(long no) {
		dao.delete(no);
	}

	public void modify(BoardVo vo) {
		dao.modify(vo);
	}

	public void write(BoardVo vo, HttpSession session) {
		if (session != null) {
			vo.setMember_no(((UserVo) session.getAttribute("authUser")).getNo());
		}
		vo.setGroup_no(dao.getMaxGroup() + 1);
		vo.setOrder_no(1);
		vo.setDepth(0);
		dao.insert(vo);
	}

	public void reply(BoardVo vo, HttpSession session) {
		if(session!=null){
			vo.setMember_no(((UserVo)session.getAttribute("authUser")).getNo());
		}
		vo.setOrder_no(vo.getOrder_no()+1);
		vo.setDepth(vo.getDepth()+1);
		dao.insert(vo);
	}

}
