package com.ctf.lab.spring.beanDefinition;

/**
 *
 *  需要注册的bean模型
 * @author H.m
 * @date 2022/8/5 10:30
 */
public class User {
	private String userName;
	private String email;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "User{" +
				"userName='" + userName + '\'' +
				", email='" + email + '\'' +
				'}';
	}
}
