package com.project.examBench.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.project.examBench.pojo.User;
import com.project.examBench.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@GetMapping("/register")
    public String register() {
        return "register";
    }

	@PostMapping("/register")
    public String register(final Model model, final User user) {
		Boolean registerSuccessfully = Boolean.FALSE;
		User dbUser = userService.save(user);
		
		if (dbUser != null) { //&&dbUser.getId() > 0
			registerSuccessfully = Boolean.TRUE;
		}
		model.addAttribute("registerSuccessfully", registerSuccessfully);
        return "register";
    }
	
	@PostMapping("/login")
    public String login(final Model model, final User user) {
		String returnPage = "home";
		User dbUser = userService.find(user);
		if (dbUser == null) {
			returnPage = "login";
			model.addAttribute("isUserNotExist", Boolean.TRUE);
		}
		model.addAttribute("user", dbUser);
        return returnPage;
    }
}
