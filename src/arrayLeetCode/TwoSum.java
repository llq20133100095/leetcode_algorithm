package arrayLeetCode;

import java.util.HashMap;
import java.util.Map;

/**
 * 1. Two Sum:
 *  Given an array of integers, return indices of the two numbers such that they add up to a specific target.
    You may assume that each input would have exactly one solution, and you may not use the same element twice.
 *
 */
public class TwoSum {

    public static int[] twoSum(int[] nums, int target) {
        Map<Integer,Integer> map=new HashMap<>();
        for(int i=0;i<nums.length;i++){
            map.put(nums[i],i);
        }

        //在HashMap中查找是否包含元素
        for(int i=0;i<nums.length-1;i++){
            int remain=target-nums[i];
            if(map.containsKey(remain) && map.get(remain)!=i){
                return new int[]{i,map.get(remain)};
            }
        }
        throw new IllegalArgumentException("No two sum solution");
    }

    public static void main(String[] args){
        int[] result=twoSum(new int[]{3,2,4},6);
        for(int x:result){
            System.out.print(x+" ");
        }
    }
}
