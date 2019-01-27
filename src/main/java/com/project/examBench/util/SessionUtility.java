package com.project.examBench.util;

import javax.servlet.http.HttpSession;

public class SessionUtility {
	
	public <T> void setIntoSession(HttpSession session, 
			String name, T value) {
		session.setAttribute(name, value);
	}
	
//	public <T extends Object> T getFromSession(HttpSession session, String name, Class.) {
//		return session.getAttribute(name);
//	}

}
