package com.project.examBench.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.project.examBench.pojo.User;
import com.project.examBench.repository.UserRepository;

@Service
public class UserService {
	@Autowired
	private UserRepository userRepository;

	private static List<User> users = new ArrayList<>();

	public final User save(User user) {
		users.add(user);
		User dbUser = null;
		dbUser = user;// has to change
		
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
