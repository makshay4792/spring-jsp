package com.project.examBench.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
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
		List<User> dbUser = namedParameterJdbcTemplate.query(sql, paramMap, (resultSet, i) -> {
            return toUser(resultSet);
        });
		if(dbUser.size()>0)
			return dbUser.get(0);
		else
			return null;
	}
	
	private User toUser(ResultSet resultSet) throws SQLException {
		User user=new User();
		user.setId(resultSet.getInt("id"));
		user.setUsername(resultSet.getString("username"));
		user.setRole(resultSet.getInt("role"));
		return user;
		
	}
	public User save(User user) {
		user.setRole(2);
		String sqlInsert = "INSERT INTO users (username,PASSWORD,role,name) VALUES (:username,MD5(:password),:role,:fullName)";
		
		namedParameterJdbcTemplate.update(sqlInsert, new BeanPropertySqlParameterSource(user));
		
		String sqlSelect="SELECT IFNULL(MAX(id),-1) FROM users";
		long userId=(long) namedParameterJdbcTemplate.queryForObject(sqlSelect,(HashMap)null ,Long.class);
		user.setId(Integer.parseInt(userId+""));
		return user;
	}

}
