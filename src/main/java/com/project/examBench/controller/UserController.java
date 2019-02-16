package com.project.examBench.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.project.examBench.pojo.Exam;
import com.project.examBench.pojo.Question;
import com.project.examBench.pojo.User;
import com.project.examBench.service.UserService;
import com.project.examBench.util.CommonUtil;
import com.project.examBench.util.SessionUtility;

@Controller
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private SessionUtility sessionUtility;
	
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
    public String login(final Model model, final User user, final HttpSession session) {
		String returnPage = "home";
		User dbUser = userService.find(user);
		if (dbUser == null) {
			returnPage = "login";
			model.addAttribute("isUserNotExist", Boolean.TRUE);
		} else {
			sessionUtility.setIntoSession(session, CommonUtil.LOGGED_IN_USER, dbUser);
		}
		model.addAttribute("user", dbUser);
        return returnPage;
    }
	
	////*******************************
	
	@GetMapping("/exams")
	public String exams(final Model model) {
		final List<Exam> exams = new ArrayList<>();
		exams.add(new Exam(1L, "AAAAA", 5, 20));
		exams.add(new Exam(1L, "BBBBBB", 15, 30));
		exams.add(new Exam(1L, "CCCCCCC", 51, 10));
		exams.add(new Exam(1L, "DDDDDDDD", 35, 40));
		exams.add(new Exam(1L, "EEEEE", 25, 30));
		
		model.addAttribute("exams", exams);
		model.addAttribute("questionCountDB", 3);
		return "examList";
	}
	
	@GetMapping("/exams/{id}")
	public String exam(final Model model, @PathVariable("id") Long id) {
		//if id is not found in DB return an empty object
		model.addAttribute("exam", new Exam());
		return "exam";
	}
	
	@GetMapping("/exams/{id}/questions")
	public String examQuestion(final Model model, @PathVariable("id") Long id) {
		final List<Question> questions = new ArrayList<>();

		questions.add(new Question(1, "QQQQQQQ1111", "AAAAAAAA1111"));
		questions.add(new Question(2, "QQQQQQQ1111", "AAAAAAAA1111"));
		questions.add(new Question(3, "QQQQQQQ1111", "AAAAAAAA1111"));
		questions.add(new Question(4, "QQQQQQQ1111", "AAAAAAAA1111"));
		questions.add(new Question(5, "QQQQQQQ1111", "AAAAAAAA1111"));
		//if id is not found in DB return an empty object
		model.addAttribute("exam", new Exam(4L, "EEEEE",  4, 30));
		model.addAttribute("questions", questions);
		return "examQuestion";
	}
	
	@PostMapping("/exams/{id}/questions")
	public String examQuestionPost(final Model model, @PathVariable("id") Long id, String examQuestions) {
		System.out.println(examQuestions);
		final List<Question> questions = new ArrayList<>();
		Arrays.asList(examQuestions.split("||*||"))
		.stream()
		.forEach(examStr -> {
			String[] elements = examStr.split("-*-");
			questions.add(new Question(Integer.valueOf(elements[0]), elements[1], elements[2]));
		});
		//model.addAttribute("exam", new Exam(4L, "EEEEE",  5, 30));
		return "examQuestion";
	}
}
