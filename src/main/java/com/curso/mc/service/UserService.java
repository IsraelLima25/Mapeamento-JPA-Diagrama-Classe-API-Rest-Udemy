package com.curso.mc.service;

import org.springframework.security.core.context.SecurityContextHolder;

import com.curso.mc.security.UserSS;

public class UserService {

	public static UserSS authenticated() {
		try {
			return (UserSS) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		} catch (Exception e) {
			return null;
		}
	}

}
