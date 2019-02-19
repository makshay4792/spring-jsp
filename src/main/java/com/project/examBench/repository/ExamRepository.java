package com.project.examBench.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class ExamRepository {

	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
}
