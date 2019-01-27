package com.project.examBench.util;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Component;

import com.project.examBench.pojo.User;

import com.project.examBench.util.CommonUtil;

@Component
public class SessionUtility {
	
	public <T> void setIntoSession(HttpSession session, 
			String name, T value) {
		session.setAttribute(name, value);
	}
	
	@SuppressWarnings("unchecked")
	public <T extends Object> T getFromSession(HttpSession session, 
			String name, Class<T> cls) {
		return (T)session.getAttribute(name);
	}
	
	public User getLoggedInUser(HttpSession session) {
		return this.getFromSession(session, CommonUtil.LOGGED_IN_USER, User.class);
	}

}
