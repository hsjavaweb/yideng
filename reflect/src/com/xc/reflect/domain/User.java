package com.xc.reflect.domain;

public class User {

	private String username;
	private String password;
	
	public void learning(){
		System.out.println("ѧϰ�ˣ�");
	}
	
	public void learning(int num){
		System.out.println("ѧϰ��"+num+"�죡");
	}
	
	public User() {
		super();
	}
	
	public User(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}

	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "User [username=" + username + ", password=" + password + "]";
	}
	
}
