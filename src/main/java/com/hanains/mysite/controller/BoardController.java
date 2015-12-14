package com.hanains.mysite.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartRequest;

import com.hanains.mysite.service.BoardService;
import com.hanains.mysite.vo.BoardVo;
import com.hanains.mysite.vo.UserVo;

@Controller
@RequestMapping("/board")
public class BoardController {

	@Autowired
	private BoardService service;
	
	@RequestMapping("/list")
	public String list(Model model,@RequestParam(value="pg", required=false) String pg, @RequestParam(value="kwd", required=false) String kwd){
		int page =1;
		if(pg != null) page = Integer.parseInt(pg);
		model.addAttribute("list",service.getListPage(page,kwd));
		model.addAttribute("boardSize", service.getBoardSize(kwd));
		model.addAttribute("pageSize", service.getPageSize());
		return "/board/list";
	}
	
	@RequestMapping("/view")
	public String view(Model model, @RequestParam("no") long no){
		service.upCount(no);
		model.addAttribute("vo", service.getView(no));
		return "/board/view";
	}
	
	@RequestMapping("/writeform")
	public String writeform(){
		return "/board/writeform";
	}
	
	@RequestMapping("/write")
	public String write(@ModelAttribute BoardVo vo, HttpSession session){
		service.write(vo,session);
		return "redirect:/board/list?pg=1";
	}
	
	@RequestMapping("/delete")
	public String delete(@RequestParam("no") long no){
		service.delete(no);
		return "redirect:/board/list?pg=1";
	}
	
	@RequestMapping("/modifyform")
	public String modifyform(Model model, @RequestParam("no") long no){
		model.addAttribute("vo", service.getView(no));
		return "/board/modifyform";
	}
	
	@RequestMapping("/modify")
	public String modify(@ModelAttribute BoardVo vo){
		service.modify(vo);
		return "redirect:/board/list?pg=1";
	}
	
	@RequestMapping("/replyform")
	public String replyform(Model model, @ModelAttribute BoardVo vo){
		model.addAttribute("vo", vo);
		return "/board/replyform";
	}
	
	@RequestMapping("/reply")
	public String reply(@ModelAttribute BoardVo vo, HttpSession session){
		service.reply(vo,session);
		return "redirect:/board/list?pg=1";
	}
}
