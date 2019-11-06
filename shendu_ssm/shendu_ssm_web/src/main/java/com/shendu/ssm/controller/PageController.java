package com.shendu.ssm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 专门用于显示页面的控制器
 */
@Controller
@RequestMapping("")
public class PageController {

	@RequestMapping("index")
	public String index() {
		return "index";
	}

	// 仅显示页面，GET
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login() {
		return "login";
	}



	@RequestMapping("unauthorized")
	public String noPerms() {
		return "403";
	}
}
