package com.hanains.mysite.controller;

import java.util.Map;

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
	
	@RequestMapping("/")
	public String list(Model model,@RequestParam(value="pg", required=true, defaultValue="1") long pg, @RequestParam(value="kwd", required=true, defaultValue="") String kwd,
			@RequestParam(value="searchType", required=false) String searchType ){
		Map<String, Object> data = service.list(pg,kwd,searchType);
		model.addAttribute("data", data);
		return "/board/list";
	}
	
	@RequestMapping("/view")
	public String view(Model model, @RequestParam("no") long no){
		service.upCount(no);
		model.addAttribute("vo", service.getView(no));
		return "/board/view";
	}
	
	@RequestMapping("/delete")
	public String delete(@RequestParam("no") long no){
		service.delete(no);
		return "redirect:/board/";
	}
	
	@RequestMapping("/modifyform")
	public String modifyform(Model model, @RequestParam("no") long no){
		model.addAttribute("vo", service.getView(no));
		return "/board/modifyform";
	}
	
	@RequestMapping("/modify")
	public String modify(@ModelAttribute BoardVo vo){
		service.modify(vo);
		return "redirect:/board/";
	}

	@RequestMapping("/writeform")
	public String writeform(Model model, @ModelAttribute BoardVo vo){
		model.addAttribute("vo", vo);
		return "/board/writeform";
	}
	
	@RequestMapping("/write")
	public String write(@ModelAttribute BoardVo vo,@RequestParam( "file" ) MultipartFile file, HttpSession session){
		service.write(vo,file,session);
		return "redirect:/board/";
	}

}
