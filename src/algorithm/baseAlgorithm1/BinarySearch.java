package algorithm.baseAlgorithm1;

import java.util.Arrays;

/**
 * 实现二分查找算法
 * Created by llq on 2017/7/16.
 */
public class BinarySearch {

    /**
     * 利用递归实现二分查找，时间复杂度为O（logN）
     * @param key
     * @param array
     * @param lo
     * @param hi
     * @return
     */
    public static int rank(int key,int[] array,int lo,int hi){
        //数组必须是有序的
        if(lo<=hi){
            int mid=(lo+hi)/2;
            if(key==array[mid]){
                return mid;
            }
            else if(key>array[mid]){
                return rank(key,array,mid+1,hi);
            }
            else if(key<array[mid]){
                return rank(key,array,lo,mid-1);
            }
        }
        return -1;
    }

    public static void main(String[] args){
        int[] list=new int[]{4,5,1,3,10,11,20,19};
        int key=5;
        Arrays.sort(list);
        int result=rank(key,list,0,list.length-1);
        System.out.println(result);
    }
}
