package com.bishi;

import java.util.Comparator;
import java.util.Scanner;
import java.util.TreeSet;

public class Second1 {

	public static void main(String[] args){
		TreeSet<Integer> ts1 = new TreeSet<Integer>(new CompareBySize());
		TreeSet<Integer> ts2 = new TreeSet<Integer>(new CompareBySize());
		Scanner sc = new Scanner(System.in);
		System.out.println("�����һ����������ݸ������������ݣ�");
		int n = sc.nextInt();
		for(int i = 0; i < n; i++ ){
			ts2.add(sc.nextInt());
		}
		System.out.println("����ڶ�����������ݸ������������ݣ�");
		int m = sc.nextInt();
		for(int i = 0; i < m; i++ ){
			ts2.add(sc.nextInt());
		}
		ts1.addAll(ts2);	//��ts2��ts1�����ݺϲ��������ϲ��������ts1
		System.out.println(ts1);	
	}
}
class CompareBySize implements Comparator<Integer>{ //ʵ��Comparator�ӿڣ���дcompare�������Լ��ĴӴ�С�������򣬲�ȥ���ظ�
	public int compare(Integer i1, Integer i2) {
		int num = i1 - i2;
		return num < 0 ? 1 : -1;
	}

}