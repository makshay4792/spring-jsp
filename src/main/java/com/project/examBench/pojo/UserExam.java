package com.project.examBench.pojo;

public class UserExam {

	private int id;
	private Exam exam;
	private int examStatus;//0 not started, 1 stated, 2 completed
	private int userId;
	private double marksObtained;
	private boolean isPass;

	public UserExam() {
	}

	public UserExam(Integer id, Exam exam, Integer examStatus, Integer userId) {
		this.id = id;
		this.exam = exam;
		this.examStatus = examStatus;
		this.userId = userId;
	}

	public double getMarksObtained() {
		return marksObtained;
	}

	public void setMarksObtained(double marksObtained) {
		this.marksObtained = marksObtained;
	}

	public boolean isPass() {
		return isPass;
	}

	public void setPass(boolean isPass) {
		this.isPass = isPass;
	}

	public void setExamStatus(int examStatus) {
		this.examStatus = examStatus;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Exam getExam() {
		return exam;
	}

	public void setExam(Exam exam) {
		this.exam = exam;
	}

	public int getExamStatus() {
		return examStatus;
	}

	public void setExamStatus(Integer examStatus) {
		this.examStatus = examStatus;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

}
