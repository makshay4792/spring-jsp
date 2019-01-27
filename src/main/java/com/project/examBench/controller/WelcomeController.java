package com.project.examBench.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class WelcomeController {

	@GetMapping("/")
	public String welcome() {
		return "login";
	}

	@GetMapping("/version")
	@ResponseBody
	public String test() {
		return "Working...";
	}

}
