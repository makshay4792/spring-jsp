package com.project.examBench.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.project.examBench.pojo.Exam;
import com.project.examBench.pojo.Question;
import com.project.examBench.pojo.UserExam;

@Repository
public class ExamRepository {

	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	
	@SuppressWarnings("unchecked")
	public int saveExam(Exam exam) {
		int examId=-1;
		try{
			examId=(int) namedParameterJdbcTemplate.queryForObject("SELECT IFNULL(id,-1) FROM exam WHERE exam.id= "+exam.getId(),(HashMap)null ,Integer.class);
		}catch(EmptyResultDataAccessException e) {
			
		}
		String sqlInsert="INSERT INTO exam (NAME,description,question_count,duration,total_marks,passing_marks)"
				+ " VALUES (:examName, :decsription, :questionCount, :durationInMin, :totalMarks, :passingMarks)";
		
		if(examId<0) {
			namedParameterJdbcTemplate.update(sqlInsert, new BeanPropertySqlParameterSource(exam));
			String sqlSelect="SELECT IFNULL(MAX(id),-1) FROM exam";
			examId=(int) namedParameterJdbcTemplate.queryForObject(sqlSelect,(HashMap)null ,Integer.class);
		}else {
			String sqlUpdate="update exam set name=:examName,question_count=:questionCount,duration=:durationInMin,"
					+ "total_marks=:totalMarks,"
					+ "passing_marks=:passingMarks where id= :id";
			namedParameterJdbcTemplate.update(sqlUpdate, new BeanPropertySqlParameterSource(exam));
		}
		return examId;
	}
	
	@SuppressWarnings("unchecked")
	public int saveQuestion(Question question) {
		int qId=-1;
		try {
			qId=(int) namedParameterJdbcTemplate.queryForObject("SELECT IFNULL(id,-1) FROM questions WHERE questions.id= "+question.getId()
			,(HashMap)null ,Integer.class);
		}catch(EmptyResultDataAccessException e) {
			
		}
		String sqlInsert ="INSERT INTO questions (question,keywords,marks,exam_id) values(:question,:keys,:maxMarks,:examCode)";
		if(qId<0) {
			namedParameterJdbcTemplate.update(sqlInsert, new BeanPropertySqlParameterSource(question));
			String sqlSelect="SELECT IFNULL(MAX(id),-1) FROM questions";
			qId=(int) namedParameterJdbcTemplate.queryForObject(sqlSelect,(HashMap)null ,Integer.class);
		}else {
			String sqlUpdate="update questions set question=:question,keywords=:keys,marks=:maxMarks where id=:id and exam_id=:examCode";
			namedParameterJdbcTemplate.update(sqlUpdate, new BeanPropertySqlParameterSource(question));
		}
		return qId;
	}
	
	public int saveResult(int userId,int examId,Exam exam) {
		String sqlInsert="INSERT INTO userexamresult (userid,examid,marks_obtained) VALUES ("+userId+","+examId+","+exam.getObtainedMarks()+")";
		namedParameterJdbcTemplate.update(sqlInsert, new BeanPropertySqlParameterSource(exam));
		return 0;
	}
	public Exam getExam(long examId) {
		String sqlSelect = "select id,name,description,question_count,duration,total_marks,passing_marks from exam where exam.id = "+examId;
		@SuppressWarnings("unchecked")
		List<Exam> examList = namedParameterJdbcTemplate.query(sqlSelect, (HashMap)null, (resultSet, i) -> {
            return toExam(resultSet);
        });
		if(examList.size()>0) {
			return examList.get(0);
		}
		return null;
	}
	
	public List<Exam> getAllExams(){
		String sqlSelect = "select id,name,description,question_count,duration,total_marks,passing_marks from exam ";
		@SuppressWarnings("unchecked")
		List<Exam> examList = namedParameterJdbcTemplate.query(sqlSelect, (HashMap)null, (resultSet, i) -> {
            return toExam(resultSet);
        });
		return examList;
	}
	public Question getQuestions(long examId,long questionId){
		String sqlselect ="select id,question,keywords,marks,exam_id,description from questions where exam_id="+examId+" and id="+questionId;
		@SuppressWarnings("unchecked")
		List<Question> qList = namedParameterJdbcTemplate.query(sqlselect, (HashMap)null, (resultSet, i) -> {
			Question q=toQuestion(resultSet);
			q.setSrNo(i+1);
            return q;
        });
		if(qList.size()>0)
			return qList.get(0);
		return null;
	}
	public List<Question> getAllQuestions(long examId){
		String sqlselect ="select id,question,keywords,marks,exam_id,description from questions where exam_id="+examId;
		@SuppressWarnings("unchecked")
		List<Question> qList = namedParameterJdbcTemplate.query(sqlselect, (HashMap)null, (resultSet, i) -> {
			Question q=toQuestion(resultSet);
			q.setSrNo(i+1);
            return q;
        });
		return qList;
	}

	public List<UserExam> getUserExams(int userId){
		String sqlSelect="SELECT DISTINCT exam.id,IFNULL(userexamresult.userid,0) AS userid,users.name AS fullname,exam.NAME,exam.description,question_count,duration,total_marks,passing_marks, " + 
				"IFNULL(userexamresult.marks_obtained,-1) AS marks_obtained " + 
				"FROM exam LEFT JOIN userexamresult ON (exam.id=userexamresult.examid AND userexamresult.userid= "+userId+ ") " + 
				"LEFT JOIN users ON (userexamresult.userid=users.id) "+
				"inner join questions on (exam.id=questions.exam_id) ";
		@SuppressWarnings("unchecked")
		List<UserExam> userExamList = namedParameterJdbcTemplate.query(sqlSelect, (HashMap)null, (resultSet, i) -> {
            return toUserExam(resultSet);
        });
		return userExamList;
	}
	
	public UserExam getUserExam(int examId,int userId){
		String sqlSelect="SELECT IFNULL(userexamresult.userid,0) AS userid,users.name AS fullname,exam.id,exam.NAME,description,question_count,duration,total_marks,passing_marks, " + 
				"IFNULL(userexamresult.marks_obtained,-1) AS marks_obtained " + 
				"FROM exam LEFT JOIN userexamresult ON (exam.id=userexamresult.examid AND userexamresult.userid= "+userId+ ") " + 
				"LEFT JOIN users ON (userexamresult.userid=users.id) where exam.id = "+examId;
		@SuppressWarnings("unchecked")
		List<UserExam> userExamList = namedParameterJdbcTemplate.query(sqlSelect, (HashMap)null, (resultSet, i) -> {
			UserExam userExam=toUserExam(resultSet);
			userExam.setSrNo(i+1);
			return userExam;
        });
		if(userExamList.size()>0) {
			return userExamList.get(0);
		}
		return null;
	}
	
	public List<UserExam> getResultForExam(int examId){
		String sqlSelect="SELECT  users.id AS userid,users.name AS fullname,exam.id,exam.NAME,description,question_count,duration,total_marks,passing_marks, " + 
				"IFNULL(userexamresult.marks_obtained,-1) AS marks_obtained " + 
				"FROM users LEFT JOIN userexamresult ON (userexamresult.userid=users.id) " + 
				"INNER JOIN exam ON (exam.id="+examId+") WHERE users.id!=1 ";
		@SuppressWarnings("unchecked")
		List<UserExam> userExamList = namedParameterJdbcTemplate.query(sqlSelect, (HashMap)null, (resultSet, i) -> {
			UserExam userExam=toUserExam(resultSet);
			userExam.setSrNo(i+1);
			if(userExam.getMarksObtained()==-1) {
				userExam.setResult("Not Appeared");
				userExam.setMarksObtained(0);
			}else {
				if(userExam.getMarksObtained()>=userExam.getExam().getPassingMarks()) {
					userExam.setResult("Pass");
				}else {
					userExam.setResult("Fail");
				}
			}
			return userExam;
        });
		
		return userExamList;
	}
	
	private UserExam toUserExam(ResultSet rs) throws SQLException {
		UserExam ue=new UserExam();
		ue.setUserId(rs.getInt("userid"));
		ue.setFullName(rs.getString("fullname"));
		Exam e=new Exam();
		e.setExamName(rs.getString("name"));
		e.setId(rs.getLong("id"));
		e.setQuestionCount(rs.getInt("question_count"));
		e.setDurationInMin(rs.getInt("duration"));
		e.setTotalMarks(rs.getDouble("total_marks"));
		e.setPassingMarks(rs.getDouble("passing_marks"));
		e.setQuestions(this.getAllQuestions(e.getId()));
		e.setQuestionCount(e.getQuestions().size());
		e.setTotalMarks(this.getTotalMarks(e.getQuestions()));
		ue.setExam(e);
		if(rs.getInt("marks_obtained")==-1) {
			ue.setExamStatus(0);
		}else {
			ue.setExamStatus(1);
		}
		ue.setMarksObtained(rs.getDouble("marks_obtained"));
		if(ue.getExam().getPassingMarks()>ue.getMarksObtained()) {
			ue.setPass(false);
		}else {
			ue.setPass(true);
		}
		return ue;
	}
	private Question toQuestion(ResultSet rs) throws SQLException {
		Question question = new Question();
		question.setDescription(rs.getString("description"));
		question.setId(rs.getInt("id"));
		String keyWords =rs.getString("keywords");
		String[] key=keyWords.split(",");
		question.setKeyWords(new ArrayList<String>(Arrays.asList(key)));
		question.setMaxMarks(rs.getDouble("marks"));
		question.setQuestion(rs.getString("question"));
		question.setExamCode(rs.getInt("exam_id"));
		question.setKeys(keyWords);
		return question;
	}
	
	private Exam toExam(ResultSet rs) throws SQLException {
		Exam exam=new Exam();
		exam.setId(rs.getLong("id"));
		exam.setDecsription(rs.getString("description"));
		exam.setDurationInMin(rs.getInt("duration"));
		exam.setExamName(rs.getString("name"));
		exam.setObtainedMarks(0);
		exam.setPassingMarks(rs.getDouble("passing_marks"));
		exam.setQuestionCount(rs.getInt("question_count"));
		exam.setTotalMarks(rs.getDouble("total_marks"));
		exam.setQuestions(this.getAllQuestions(exam.getId()));
		exam.setQuestionCount(exam.getQuestions().size());
		exam.setTotalMarks(this.getTotalMarks(exam.getQuestions()));
		return exam;
	}
	
	private double getTotalMarks(List<Question> questionList) {
		double totalMarks=0;
		for(Question q:questionList) {
			totalMarks+=q.getMaxMarks();
		}
		return totalMarks;
	}
}
