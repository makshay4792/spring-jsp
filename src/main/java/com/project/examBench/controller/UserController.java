package com.project.examBench.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.project.examBench.pojo.Exam;
import com.project.examBench.pojo.Question;
import com.project.examBench.pojo.User;
import com.project.examBench.pojo.UserExam;
import com.project.examBench.pojo.UserExamResult;
import com.project.examBench.service.ExamService;
import com.project.examBench.service.UserService;
import com.project.examBench.util.CommonUtil;
import com.project.examBench.util.SessionUtility;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;

	@Autowired
	private ExamService examService;

	@Autowired
	private SessionUtility sessionUtility;

	@GetMapping("/register")
	public String register() {
		return "register";
	}

	@PostMapping("/register")
	public String register(final Model model, @ModelAttribute("user") User user) {
		Boolean registerSuccessfully = Boolean.FALSE;
		User dbUser = userService.save(user);

		if (dbUser != null) { // &&dbUser.getId() > 0
			registerSuccessfully = Boolean.TRUE;
		}
		model.addAttribute("registerSuccessfully", registerSuccessfully);
		if (registerSuccessfully) {
			return "login";
		}
		return "register";
	}

	@PostMapping("/logout")
	public String login(final Model model, HttpSession session) {
		session.invalidate();
		return "login";
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
			if (dbUser.getRole() == 1) {
				model.addAttribute("exams", examService.getAllExams());
				returnPage = "examList";
			} else {
				model.addAttribute("userExams", examService.getUserExams(dbUser.getId()));
				model.addAttribute("userId", dbUser.getId());
				returnPage = "userExamList";
			}
		}
		model.addAttribute("user", dbUser);
		return returnPage;
	}

	@GetMapping("/examlist")
	public String examlist(final Model model, HttpSession session) {
		model.addAttribute("exams", examService.getAllExams());
		return "examList";
	}

	@PostMapping("/updateExams/{id}")
	public String updateExam(@ModelAttribute("userDto") Exam exam, Model model, @PathVariable("id") Long id,
			HttpSession session) {
		String returnPage = "login";
		if (session.getAttribute(CommonUtil.LOGGED_IN_USER) != null) {
			Map<String, Object> modelMap = model.asMap();
			examService.saveExam(exam);
			model.addAttribute("exams", examService.getAllExams());
			returnPage = "examList";
		} else {
			returnPage = "login";
		}
		return returnPage;

	}

	@GetMapping("/exams")
	public String exams(final Model model, HttpSession session) {
		if (session.getAttribute(CommonUtil.LOGGED_IN_USER) != null) {
			final List<UserExam> userExams = new ArrayList<>();
			userExams.add(new UserExam(1, new Exam(1L, "AAAAA", 5, 20), 1, 1));
			userExams.add(new UserExam(2, new Exam(1L, "BBBBBB", 15, 30), 0, 10));
			userExams.add(new UserExam(3, new Exam(1L, "CCCCCCC", 51, 10), 0, 101));
			userExams.add(new UserExam(4, new Exam(1L, "DDDDDDDD", 35, 40), 2, 20));
			userExams.add(new UserExam(5, new Exam(1L, "EEEEE", 25, 30), 1, 60));

			model.addAttribute("userExams", userExams);
			model.addAttribute("questionCountDB", 3);
			return "userExamList";
		} else {
			return "login";
		}
	}

	@GetMapping("/exams/{id}")
	public String exam(final Model model, @PathVariable("id") Long id, HttpSession session) {
		// if id is not found in DB return an empty object
		if (session.getAttribute(CommonUtil.LOGGED_IN_USER) != null) {
			Exam exam = examService.getExam(id);
			model.addAttribute("exam", exam);
			return "exam";
		} else {
			return "login";
		}
	}

	@GetMapping("/exams/{id}/questions")
	public String getAllQuestions(final Model model, @PathVariable("id") long id, HttpSession session) {
		if (session.getAttribute(CommonUtil.LOGGED_IN_USER) != null) {
			final List<Question> questions = examService.getAllQuestions(id);
			model.addAttribute("examId", id);
			model.addAttribute("questions", questions);
			return "questionList";
		} else {
			return "login";
		}
	}

	@GetMapping("/exams/{examId}/questions/{id}")
	public String getQuestion(Model model, @PathVariable("examId") long examId, @PathVariable("id") long id,
			HttpSession session) {
		if (session.getAttribute(CommonUtil.LOGGED_IN_USER) != null) {
			Question question = examService.getQuestions(examId, id);
			model.addAttribute("examId", examId);
			model.addAttribute("question", question);
			return "question";
		} else {
			return "login";
		}
	}

	@PostMapping("/exams/{examId}/questions/{id}")
	public String updateQuestion(@ModelAttribute("questionDto") Question question, Model model,
			@PathVariable("examId") int examId, @PathVariable("id") int id, HttpSession session) {
		String returnPage = "login";
		if (session.getAttribute(CommonUtil.LOGGED_IN_USER) != null) {
			question.setExamCode(examId);
			examService.saveQuestion(question);
			model.addAttribute("questions", examService.getAllQuestions(examId));
			model.addAttribute("examId", examId);
			returnPage = "questionList";
		} else {
			returnPage = "login";
		}
		return returnPage;
	}
	
	@GetMapping("/exams/{examId}/questions/delete/{id}")
	public String deleteQuestion(Model model,
			@PathVariable("examId") int examId, @PathVariable("id") int id, HttpSession session) {
		String returnPage = "login";
		Question question=new Question();
		if (session.getAttribute(CommonUtil.LOGGED_IN_USER) != null) {
			question.setExamCode(examId);
			question.setId(id);
			examService.deleteQuestion(question);
			model.addAttribute("questions", examService.getAllQuestions(examId));
			model.addAttribute("examId", examId);
			returnPage = "questionList";
		} else {
			returnPage = "login";
		}
		return returnPage;
	}

	@GetMapping("/questionpaper/{examId}/{userId}")
	public String takeExam(final Model model, @PathVariable("examId") int examId, @PathVariable("userId") int userId,
			HttpSession session) {
		if (session.getAttribute(CommonUtil.LOGGED_IN_USER) != null) {
			UserExam userExam = examService.getUserExam(examId, userId);
			model.addAttribute("exam", userExam.getExam());
			model.addAttribute("questionCount", userExam.getExam().getQuestionCount());
			model.addAttribute("questions", userExam.getExam().getQuestions());
			model.addAttribute("userId", userId);
			model.addAttribute("timerinMinute", userExam.getExam().getDurationInMin());
			if (userExam.getExam().getPassingMarks() <= userExam.getExam().getObtainedMarks()) {
				model.addAttribute("result","Pass");
			}else {
				model.addAttribute("result","Fail");
			}
			if (userExam.getMarksObtained() == -1) {
				return "userExamQuestion";
			} else {
				return "result";
			}
		} else {
			return "login";
		}
	}

	@GetMapping("/examresult/{examId}")
	public String getResultForExam(final Model model, @PathVariable("examId") int examId,
			HttpSession session) {
		if (session.getAttribute(CommonUtil.LOGGED_IN_USER) != null) {
			List<UserExam> examResults=examService.getResultForExam(examId);
			//examResults=examService.filterUserExamList(examResults);
			model.addAttribute("examResults", examResults);
			return "userExamResult";
		}else {
			return "login";
		}
	}
	@PostMapping("/exams/evaluate/{examId}/{userId}")
	public String examQuestionPost(final Model model, String examQuestions, @PathVariable("examId") int examId,
			@PathVariable("userId") int userId, HttpSession session) {
		if (session.getAttribute(CommonUtil.LOGGED_IN_USER) != null) {
			Exam exam = examService.getExam(examId);
			String result = "Fail";
			if (examQuestions.contains("#") && examQuestions.contains("@")) {
				exam = this.getAnswers(exam, examQuestions);
				exam = examService.evaluate(exam);
				if (exam.getPassingMarks() <= exam.getObtainedMarks()) {
					result = "Pass";
				}
				model.addAttribute("exam", exam);
				model.addAttribute("result", result);
				model.addAttribute("questions", exam.getQuestions());
				examService.saveResult(userId, examId, exam);
			}
			// call Result page here
 			return "result";
		} else {
			return "login";
		}
	}

	private Exam getAnswers(Exam exam, String answers) {
		String[] questionAnswer = answers.split("#");
		String[] question = null;
		List<Question> questionList = exam.getQuestions();
		if (questionList.size() == questionAnswer.length) {
			for (int i = 0; i < questionAnswer.length; i++) {
				question = questionAnswer[i].split("@");
				if (question != null && question.length > 2) {
					questionList.get(i).setAnswer(question[2]);
				}
			}
		}
		exam.setQuestions(questionList);
		return exam;
	}

	@GetMapping("/userExamResult")
	public String getUserExamResult(Model model) {
		List<UserExamResult> userExamResults = new ArrayList<>();
		userExamResults.add(new UserExamResult(1, "Mohan", "English", 10, 20, 11.2));
		userExamResults.add(new UserExamResult(2, "Gohan", "Wnglish", 10, 20, 15.9));
		userExamResults.add(new UserExamResult(3, "Sohan", "Pnglish", 30, 20, 16.2));
		userExamResults.add(new UserExamResult(4, "Tohan", "Ghnglish", 10, 20, 15.8));
		userExamResults.add(new UserExamResult(5, "Pohan", "Snglish", 40, 20, 17.7));
		model.addAttribute("userExamResults", userExamResults);
		return "userExamResult";
	}
}