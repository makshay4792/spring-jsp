package com.project.examBench.pojo;

public class UserExam {

	private Integer id;
	private Exam exam;
	private Integer examStatus;//0 not started, 1 stated, 2 completed
	private Integer userId;

	public UserExam() {
	}

	public UserExam(Integer id, Exam exam, Integer examStatus, Integer userId) {
		this.id = id;
		this.exam = exam;
		this.examStatus = examStatus;
		this.userId = userId;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Exam getExam() {
		return exam;
	}

	public void setExam(Exam exam) {
		this.exam = exam;
	}

	public Integer getExamStatus() {
		return examStatus;
	}

	public void setExamStatus(Integer examStatus) {
		this.examStatus = examStatus;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

}
