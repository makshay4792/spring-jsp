package com.project.examBench.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.examBench.pojo.Exam;
import com.project.examBench.pojo.Question;
import com.project.examBench.pojo.UserExam;
import com.project.examBench.repository.ExamRepository;
import com.project.examBench.util.CommonUtil;

@Service
public class ExamService {

	@Autowired
	private ExamRepository examRepository;
	
	public int saveExam(Exam exam) {
		int examid=examRepository.saveExam(exam);
		return examid;
	}
	
	public int saveQuestion(Question question) {
		int qId=examRepository.saveQuestion(question);
		return qId;
	}
	
	public int deleteQuestion(Question question) {
		return examRepository.deleteQuestion(question);
	}
	
	public Exam getExam(long examId) {
		Exam exam=examRepository.getExam(examId);
		if(exam==null) {
			exam=new Exam();
			exam.setExamName("");
			exam.setId(0l);
			exam.setDurationInMin(0);
			exam.setPassingMarks(0);
			exam.setQuestionCount(0);
			exam.setTotalMarks(0);
			return exam;
		}
		return examRepository.getExam(examId);
	}
	
	public List<UserExam> getUserExams(int userId){
		return examRepository.getUserExams(userId);
	}
	
	public UserExam getUserExam(int examId,int userId){
		return examRepository.getUserExam(examId,userId);
	}
	
	public List<Exam> getAllExams(){
		return examRepository.getAllExams();
	}
	
	public List<Question> getAllQuestions(long examId){
		return examRepository.getAllQuestions(examId);
	}
	
	public Question getQuestions(long examId,long questionId){
		Question question=examRepository.getQuestions(examId,questionId);
		if(question==null) {
			question=new Question();
			question.setAnswer("");
			question.setQuestion("");
			question.setKeys("");
			question.setMaxMarks(0);
		}
		return question;
	}
	
	public int saveResult(int userId,int examId,Exam exam) {
		return examRepository.saveResult(userId, examId, exam);
	}
	
	public List<UserExam> getResultForExam(int examId){
		return examRepository.getResultForExam(examId);
	}
	
	public List<UserExam> filterUserExamList(List<UserExam> examList){
		List<UserExam> tempList=new ArrayList<UserExam>();
		int index=0;
		for(UserExam ue:examList) {
			if(ue.getMarksObtained()>=0) {
				ue.setSrNo(++index);
				tempList.add(ue);
			}
		}
		return tempList;
	}
	public Exam evaluate(Exam exam) {
		double marks=0;
		for(Question q:exam.getQuestions()) {
			if( q.getAnswer()==null || "".equalsIgnoreCase(q.getAnswer())) {
				q.setObtainedMarks(0);
			}else {
				q.setObtainedMarks(this.calculateMarks(q.getKeyWords(), q.getAnswer(), q.getMaxMarks()));
			}
			marks+=q.getObtainedMarks(); 
		}
		exam.setObtainedMarks(marks);
		return exam;
	}
	private double calculateMarks(List<String> keyWords,String answer,double maxMarks) {
		double obtainedMarks = 0;
		int rightSequence = 0;
		answer = answer.toLowerCase();
		Map<String, Integer> keyMap = new HashMap<String, Integer>();
		List<String> keyWordsTemp = new ArrayList<>(keyWords);
		for (String s : keyWords) {
			if (answer.contains(s.toLowerCase())) {
				keyMap.put(s, answer.indexOf(s.toLowerCase()));
			} else {
				keyWordsTemp.remove(s);
			}
		}
		Map<String, Integer> sortedKeyMap = CommonUtil.sortByValue(keyMap);
		int index = 0;
		if (sortedKeyMap.size() == keyWordsTemp.size()) {
			for (Map.Entry<String, Integer> entry : sortedKeyMap.entrySet()) {
				if (entry.getKey().equalsIgnoreCase(keyWordsTemp.get(index))) {
					rightSequence++;
				}
				index++;
			}
		}
		obtainedMarks = (maxMarks / keyWords.size()) * rightSequence;
		return obtainedMarks;
	}
}
