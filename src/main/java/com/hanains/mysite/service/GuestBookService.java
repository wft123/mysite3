package com.hanains.mysite.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hanains.mysite.dao.GuestBookDao;
import com.hanains.mysite.vo.GuestBookVo;

@Service
public class GuestBookService {

	@Autowired
	private GuestBookDao dao;

	public List<GuestBookVo> getList() {
		return dao.getGuestBook();
	}

	public void add(GuestBookVo vo) {
		dao.add(vo);		
	}

	public void delete(GuestBookVo vo) {
		dao.delete(vo);
	}
}
