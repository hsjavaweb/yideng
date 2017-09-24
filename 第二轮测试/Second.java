package com.bishi;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
public class Second {
    public static void main(String[] agse) {
        List<Integer> list1 = new LinkedList<Integer>();
        Collections.addAll(list1, 30, 41, 15, 12, 56, 80);
        List<Integer> list2 = new LinkedList<Integer>();
        Collections.addAll(list2, 56, 78, 23, 12, 33, 79, 90, 55);
        List<Integer> list = test1(list1, list2);
        for(int i : list){
        	System.out.print(i +" ");
        }
    }
	 
    public static List<Integer> test1(List<Integer> list1, List<Integer> list2) {
        list2.addAll(list1);// ºÏ²¢
        Collections.sort(list2);
        LinkedList<Integer> list = new LinkedList<Integer>();
        for (int i : list2) {
            list.push(i);
        }
        return list;
    }
    
}

