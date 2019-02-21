package com.project.examBench.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.project.examBench.pojo.User;
import com.project.examBench.repository.UserRepository;

@Service
public class UserService {
	@Autowired
	private UserRepository userRepository;

	public User save(User user) {
		User dbUser = null;
		dbUser = userRepository.save(user);
		return dbUser;
	}

	public User find(final User user) {
		User dbUser = null;
		try {
			dbUser = userRepository.find(user);
		} catch (EmptyResultDataAccessException e) {
		}

		return dbUser;
	}
}