package com.project.examBench.pojo;

public class UserExamResult {

	private Integer id;
	private String userName;
	private String examName;
	private Integer noOfQuestions;
	private Integer actualMarks;
	private Double optainMarks;
	
	public UserExamResult() {
	}

	public UserExamResult(Integer id, String userName, String examName, Integer noOfQuestions, Integer actualMarks,
			Double optainMarks) {
		this.id = id;
		this.userName = userName;
		this.examName = examName;
		this.noOfQuestions = noOfQuestions;
		this.actualMarks = actualMarks;
		this.optainMarks = optainMarks;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getExamName() {
		return examName;
	}

	public void setExamName(String examName) {
		this.examName = examName;
	}

	public Integer getNoOfQuestions() {
		return noOfQuestions;
	}

	public void setNoOfQuestions(Integer noOfQuestions) {
		this.noOfQuestions = noOfQuestions;
	}

	public Integer getActualMarks() {
		return actualMarks;
	}

	public void setActualMarks(Integer actualMarks) {
		this.actualMarks = actualMarks;
	}

	public Double getOptainMarks() {
		return optainMarks;
	}

	public void setOptainMarks(Double optainMarks) {
		this.optainMarks = optainMarks;
	}
	
	

}