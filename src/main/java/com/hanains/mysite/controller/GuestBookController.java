package com.hanains.mysite.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hanains.mysite.service.GuestBookService;
import com.hanains.mysite.vo.GuestBookVo;
@Controller
@RequestMapping("/guestbook")
public class GuestBookController {

	private static final Log LOG = LogFactory.getLog( GuestBookController.class );
	
	@Autowired
	private GuestBookService service;
	
	@RequestMapping("/")
	public String list(Model model){
		LOG.error("GuestBookController.list");
		model.addAttribute("list",service.getList());
		return "/guestbook/list";
	}
	
	@RequestMapping("/add")
	public String add(@ModelAttribute GuestBookVo vo){
		if(vo.getMessage().trim().length()==0 || vo.getName().trim().length()==0 || vo.getPassword().trim().length()==0) return "redirect:/guestbook/";
		service.add(vo);
		return "redirect:/guestbook/";
	}
	
	@RequestMapping("/deleteform")
	public String deleteform(){
		return "/guestbook/deleteform";
	}
	
	@RequestMapping("/delete")
	public String delete(@ModelAttribute GuestBookVo vo){
		service.delete(vo);
		return "redirect:/guestbook/";
	}
}
