package com.xc.domain;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
public class Student{
	
	@MyAnnotation(name="id",type="int",primarykey="key")
	private int studentId;
	
	@MyAnnotation(name="name")
	private String name;
	
	@MyAnnotation(name="age")
	private int age;
	
	@MyAnnotation(name="password")
	private String password;
}
