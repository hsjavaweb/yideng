package com.bishi;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class third {
	//���ҳ������ַ���˳���������ַ������������ӣ�ghbcgajjfa  uyckhcxaf -> hcaf  
		public static void main(String[] args) {  
			Scanner in = new Scanner(System.in);  
	        String str1 = in.nextLine();  		//�����һ���ַ���
	        String str2 = in.nextLine();  		//����ڶ����ַ���
	        List<String> str = getString(str1,str2);//����getString��������������ַ���˳���������ַ�
	        int i = 1;
	        for(String s : str){
	        	System.out.println("��"+i+++"�������ִ�:"+s);
	        }
			
		} 
		
		public static List<String> getString(String str1,String str2){
			String str3 = "";
			List<String> list = new ArrayList<String>();
			char[] arr1=str1.toCharArray();
			char[] arr2=str2.toCharArray();			//�ַ���ת����
			int t=0;
			for(int i = 0; i < arr1.length; i++){	
				for(int j = t; j < arr2.length; j++){
					if(arr1[i] == arr2[j]){			//�ҳ��ڶ����������һ����������i��ͬ���ַ�
						str3+=arr2[j];
						t = j + 1;					//��¼�ڶ��������ʱ����һ���������Ա��´�ѭ���ڶ�������ʱ�ӵ�ǰ������ʼ
						break;						//����ѭ��������һ���ҳ���ͬ���ַ�
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

