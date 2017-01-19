package com.jerry.sample.frame.okhttp;

public class User {

	public String username ; 
	public String age  ;
	
	public User() {
		// TODO Auto-generated constructor stub
	}
	
	public User(String username, String age) {
		this.username = username;
		this.age = age;
	}

	@Override
	public String toString() {
		return "User{" +
				"username='" + username + '\'' +
				", age='" + age + '\'' +
				'}';
	}
}
