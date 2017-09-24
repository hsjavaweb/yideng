package com.bishi;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class third {
	//请找出两个字符串顺序连续的字符，如以下例子，ghbcgajjfa  uyckhcxaf -> hcaf  
		public static void main(String[] args) {  
			Scanner in = new Scanner(System.in);  
	        String str1 = in.nextLine();  		//输入第一次字符串
	        String str2 = in.nextLine();  		//输入第二个字符串
	        List<String> str = getString(str1,str2);//调用getString函数，输出两个字符串顺序连续的字符
	        int i = 1;
	        for(String s : str){
	        	System.out.println("第"+i+++"个连续字串:"+s);
	        }
			
		} 
		
		public static List<String> getString(String str1,String str2){
			String str3 = "";
			List<String> list = new ArrayList<String>();
			char[] arr1=str1.toCharArray();
			char[] arr2=str2.toCharArray();			//字符串转数组
			int t=0;
			for(int i = 0; i < arr1.length; i++){	
				for(int j = t; j < arr2.length; j++){
					if(arr1[i] == arr2[j]){			//找出第二个数组与第一个数组索引i相同的字符
						str3+=arr2[j];
						t = j + 1;					//记录第二个数组此时的下一个索引，以便下次循环第二个数组时从当前索引开始
						break;						//结束循环避免又一次找出相同的字符
					}
				}
				if(t == arr2.length || i == arr1.length - 1){		
					if(str3.length()>1){
						list.add(str3);
					}
					str3 = "";
					t = 0;
				}
			}
			return list;								
		}
	}

