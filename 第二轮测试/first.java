package com.bishi;

public class first {

	public static void main(String[] args){
		int[] arr = {2,4,6};
		boolean sum = groupSum(0, arr, 10);
		System.out.println(sum);
	}
	public static boolean groupSum(int start,int[] nums,int target){
		if(target==0)
			return true;
		if(start==nums.length)
			return false;
		if(groupSum(start+1,nums,target-nums[start])){
			return true;
		}else{
			return groupSum(start+1,nums,target);
		}
	}
}
