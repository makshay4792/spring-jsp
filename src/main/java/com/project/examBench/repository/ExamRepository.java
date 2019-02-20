package com.project.examBench.repository;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.project.examBench.pojo.Exam;

@Repository
public class ExamRepository {

	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	
	public int saveExam(Exam exam) {
		int examId=0;
		String sqlInsert="INSERT INTO exam (NAME,description,question_count,duration,total_marks,passing_marks)"
				+ " VALUES (:examName, :decsription, :questionCount, :durationInMin, :totalMarks, :passingMarks)";
		
		namedParameterJdbcTemplate.update(sqlInsert, new BeanPropertySqlParameterSource(exam));
		
		String sqlSelect="SELECT IFNULL(MAX(id),-1) FROM exam";
		examId=(int) namedParameterJdbcTemplate.queryForObject(sqlSelect,(HashMap)null ,Integer.class);
		return examId;
	}
	
	public Exam getExam(int examId) {
		return null;
	}
	
	public List<Exam> getAllExams(){
		return null;
	}
}
