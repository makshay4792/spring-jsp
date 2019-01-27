package com.project.examBench.service;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.project.examBench.pojo.User;
import com.project.examBench.util.SessionUtility;
//import com.project.examBench.repository.UserRepository;

import com.project.examBench.util.CommonUtil;

@Service
public class UserService {
//	@Autowired
//	private UserRepository userRepository;
	
	@Autowired
	private SessionUtility sessionUtility;

	private static List<User> users = new ArrayList<>();

	public final User save(User user, HttpSession session) {
		users.add(user);
		User dbUser = null;
		sessionUtility.setIntoSession(session, CommonUtil.LOGGED_IN_USER, dbUser);
		dbUser = user;// has to change
		
		return dbUser;
	}

	public User find(final User user) {
		User dbUser = null;
		try {
//			dbUser = userRepository.findUser(user);
		} catch (EmptyResultDataAccessException e) {
		}

		return dbUser;
	}

}
