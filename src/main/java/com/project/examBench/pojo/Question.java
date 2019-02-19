package com.project.examBench.pojo;

public class Question {

	private Integer id;
	private String question;
	private String keyWords;
	private String answer;

	public Question() {
	}
	
	public Question(Integer id, String question) {
		this.id = id;
		this.question = question;
	}

	public Question(Integer id, String question, String keyWords) {
		this(id, question);
		this.keyWords = keyWords;
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

	public String getKeyWords() {
		return keyWords;
	}

	public void setKeyWords(String keyWords) {
		this.keyWords = keyWords;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	@Override
	public String toString() {
		return "Question [id=" + id + ", question=" + question + ", keyWords=" + keyWords + ", answer=" + answer + "]";
	}

}