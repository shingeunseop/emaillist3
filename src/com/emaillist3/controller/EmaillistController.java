package com.emaillist3.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.emaillist.dao.EmaillistDao;
import com.emaillist.vo.EmaillistVo;

@Controller
public class EmaillistController {
	
	@Autowired private EmaillistDao dao;

	@RequestMapping("/form")
	public String form(@ModelAttribute EmaillistVo vo) {
		System.out.println(vo.toString());
		
		
		
		return "/WEB-INF/views/form.jsp";
	}
	
	@RequestMapping("/add")
	public String add(@ModelAttribute EmaillistVo vo) {
		dao.insert(vo);
		
		return "redirect:/list";
	}
	
	
}
