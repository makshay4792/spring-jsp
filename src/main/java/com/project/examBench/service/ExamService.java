package com.project.examBench.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.examBench.pojo.Exam;
import com.project.examBench.pojo.Question;
import com.project.examBench.repository.ExamRepository;
import com.project.examBench.util.CommonUtil;

@Service
public class ExamService {

	@Autowired
	private ExamRepository examRepository;
	
	private Exam evaluate(Exam exam) {
		double marks=0;
		for(Question q:exam.getQuestions()) {
			q.setObtainedMarks(this.calculateMarks(q.getKeyWords(), q.getAnswer(), q.getMaxMarks()));
			marks+=q.getObtainedMarks();
		}
		exam.setObtainedMarks(marks);
		return exam;
	}
	private double calculateMarks(List<String> keyWords,String answer,double maxMarks) {
		double obtainedMarks=0;
		int rightSequence=0;
		answer=answer.toLowerCase();
		Map<String,Integer> keyMap=new HashMap<String, Integer>();
		for(String s:keyWords) {
			if(answer.contains(s)){
				keyMap.put(s, answer.indexOf(s));
			}
		}
		Map<String,Integer> sortedKeyMap=CommonUtil.sortByValue(keyMap);
		int index=0;
		for(Map.Entry<String, Integer> entry:sortedKeyMap.entrySet()) {
			if(entry.getKey().equalsIgnoreCase(keyWords.get(index))) {
				rightSequence++;
			}
			index++;
		}
		obtainedMarks=(maxMarks/keyWords.size())*rightSequence;
		return obtainedMarks;
	}
}
