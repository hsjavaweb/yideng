package com.xc.reflect.test;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class Demo {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new FileReader("a.properties"));
		Class<?> clazz = Class.forName(br.readLine());
		Fruit f = (Fruit) clazz.newInstance();
		Juicer j = new Juicer();
		j.run(f);
	}

}
interface Fruit{
	public void squeeze();
}

class Banana implements Fruit{

	@Override
	public void squeeze() {
		System.out.println("Ïã½¶");
	}
	
}

class Orange implements Fruit{

	@Override
	public void squeeze() {
		System.out.println("éÙ×Ó");
	}
	
}

class Juicer {
	public void run(Fruit f){
		f.squeeze();
	}
}