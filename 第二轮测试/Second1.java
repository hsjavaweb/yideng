package com.bishi;

import java.util.Comparator;
import java.util.Scanner;
import java.util.TreeSet;

public class Second1 {

	public static void main(String[] args){
		TreeSet<Integer> ts1 = new TreeSet<Integer>(new CompareBySize());
		TreeSet<Integer> ts2 = new TreeSet<Integer>(new CompareBySize());
		Scanner sc = new Scanner(System.in);
		System.out.println("输入第一个链表的数据个数并输入数据：");
		int n = sc.nextInt();
		for(int i = 0; i < n; i++ ){
			ts2.add(sc.nextInt());
		}
		System.out.println("输入第二个链表的数据个数并输入数据：");
		int m = sc.nextInt();
		for(int i = 0; i < m; i++ ){
			ts2.add(sc.nextInt());
		}
		ts1.addAll(ts2);	//将ts2喝ts1的数据合并，并将合并结果赋给ts1
		System.out.println(ts1);	
	}
}
class CompareBySize implements Comparator<Integer>{ //实现Comparator接口，重写compare，按照自己的从大到小方法排序，不去处重复
	public int compare(Integer i1, Integer i2) {
		int num = i1 - i2;
		return num < 0 ? 1 : -1;
	}

}