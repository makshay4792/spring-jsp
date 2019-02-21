package com.project.examBench.pojo;

import java.util.List;

public class Question {

	private Integer id;
	private int srNo;
	private String question;
	private String description;
	private List<String> keyWords;
	private String keys;
	private String answer;
	private double maxMarks;
	private double obtainedMarks;
	private String modelAnswer;
	private int examCode;

	public Question() {
	}
	
	public String getKeys() {
		return keys;
	}

	public void setKeys(String keys) {
		this.keys = keys;
	}

	public Question(Integer id, String question) {
		this.id = id;
		this.question = question;
	}

	public int getExamCode() {
		return examCode;
	}

	public void setExamCode(int examCode) {
		this.examCode = examCode;
	}

	public int getSrNo() {
		return srNo;
	}

	public void setSrNo(int srNo) {
		this.srNo = srNo;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<String> getKeyWords() {
		return keyWords;
	}

	public void setKeyWords(List<String> keyWords) {
		this.keyWords = keyWords;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public double getMaxMarks() {
		return maxMarks;
	}

	public void setMaxMarks(double maxMarks) {
		this.maxMarks = maxMarks;
	}

	public double getObtainedMarks() {
		return obtainedMarks;
	}

	public void setObtainedMarks(double obtainedMarks) {
		this.obtainedMarks = obtainedMarks;
	}

	public String getModelAnswer() {
		return modelAnswer;
	}

	public void setModelAnswer(String modelAnswer) {
		this.modelAnswer = modelAnswer;
	}

}