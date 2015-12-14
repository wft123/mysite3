package com.hanains.mysite.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.hanains.mysite.service.BoardService;
import com.hanains.mysite.vo.BoardVo;

@Controller
@RequestMapping("/board")
public class BoardController {

	@Autowired
	private BoardService service;
	
	@RequestMapping("/list")
	public String list(Model model,@RequestParam(value="pg", required=false) String pg, @RequestParam(value="kwd", required=false) String kwd){
		model = service.list(model,pg,kwd);
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
	public String reply(@ModelAttribute BoardVo vo){
		service.reply(vo);
		return "redirect:/board/list?pg=1";
	}
	
	@RequestMapping("/write")
	public String write(@ModelAttribute BoardVo vo){
		service.write(vo);
		return "redirect:/board/list?pg=1";
	}
	
	
	@RequestMapping("/upload")
	public String upload(@ModelAttribute BoardVo vo,@RequestParam( "file" ) MultipartFile file, HttpSession session){
		service.upload(vo,file,session);
		return "redirect:/board/list?pg=1";
	}
	
	@RequestMapping("/replyupload")
	public String replyupload(@ModelAttribute BoardVo vo, @RequestParam( "file" ) MultipartFile file, HttpSession session){
		service.replyupload(vo,file,session);
		return "redirect:/board/list?pg=1";
	}
}
