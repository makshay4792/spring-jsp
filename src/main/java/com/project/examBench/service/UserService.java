package com.project.examBench.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.project.examBench.pojo.User;

@Service
public class UserService {
	
	private static List<User> users = new ArrayList<>();

	public final User save(User user) {
		users.add(user);
		User dbUser = null;
		dbUser = user;//has to change
		return dbUser;
	}
	
	public User findByEmailAndPassword(final User user) {
		return users.stream().
				filter(u -> (user.getEmail().equals(u.getEmail()) &&user.getPassword().equals(u.getPassword())))
				.findFirst().orElse(null);
	}

}
