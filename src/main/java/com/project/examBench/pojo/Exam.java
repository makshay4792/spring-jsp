package com.project.examBench.pojo;

public class Exam {

	private Long id;
	private String exam;
	private Integer questionCount;
	private Integer durationInMin;

	public Exam() {
	}

	public Exam(Long id, String exam, Integer questionCount, Integer durationInMin) {
		super();
		this.id = id;
		this.exam = exam;
		this.questionCount = questionCount;
		this.durationInMin = durationInMin;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getExam() {
		return exam;
	}

	public void setExam(String exam) {
		this.exam = exam;
	}

	public Integer getQuestionCount() {
		return questionCount;
	}

	public void setQuestionCount(Integer questionCount) {
		this.questionCount = questionCount;
	}

	public Integer getDurationInMin() {
		return durationInMin;
	}

	public void setDurationInMin(Integer durationInMin) {
		this.durationInMin = durationInMin;
	}

}
