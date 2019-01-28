package com.project.examBench.repository;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.project.examBench.pojo.User;

@Repository
public class UserRepository {

	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	
	public User find(final User user) throws EmptyResultDataAccessException {
		String sql = "SELECT * FROM users WHERE username=:username AND password=md5(:password)";
		
		Map<String, String> paramMap=new HashMap<>();
		paramMap.put("username", user.getUsername());
		paramMap.put("password", user.getPassword());
		User dbUser = namedParameterJdbcTemplate.queryForObject(sql, paramMap, User.class);
		
		return dbUser;
	}

}
