package com.hanains.mysite.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hanains.mysite.service.UserService;
import com.hanains.mysite.vo.UserVo;

@Controller
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@RequestMapping("/joinform")
	public String joinform(){
		return "/user/joinform";
	}
	
	@ResponseBody
	@RequestMapping("/checkEmail")
	public Object checkEmail(@RequestParam( value="email", required=true, defaultValue="") String email){
		UserVo vo = userService.getUser(email);
		
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("result", "success"); //success or fail
		map.put("message", null); // fail message
		map.put("data", vo==null); // vo가 없으면 사용가능한 email
		
		return map;
	}
	
	@RequestMapping("/join")
	public String join(@ModelAttribute UserVo vo){
		if(vo.getEmail().trim().length()==0 || vo.getName().trim().length()==0 || vo.getPassword().trim().length()==0) return "/user/joinform";
		userService.join(vo);
		return "redirect:/user/joinsuccess";
	}
	
	@RequestMapping("/joinsuccess")
	public String joinSuccess(){
		return "/user/joinsuccess";
	}
	
	@RequestMapping("/loginform")
	public String loginform(){
		return "/user/loginform";
	}
}
