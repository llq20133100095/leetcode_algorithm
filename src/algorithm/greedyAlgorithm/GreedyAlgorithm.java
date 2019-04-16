package algorithm.greedyAlgorithm;

import java.util.LinkedList;
import java.util.List;

/**
 * greedy algorithm贪心算法
 * Created by llq on 2017/7/20.
 */
public class GreedyAlgorithm {

    /**
     * 设有N个活动时间集合，每个活动都要使用同一个资源，比如说会议场，而且同一时间内只能有一个活动使用，
     * 每个活动都有一个使用活动的开始si和结束时间fi，即他的使用区间为（si,fi）,
     * 现在要求你分配活动占用时间表，即哪些活动占用该会议室，哪些不占用，使得他们不冲突，
     * 要求是尽可能多的使参加的活动最大化，即所占时间区间最大化！
     * @param start
     * @param end
     * @return
     */
    public static List<Integer> arrangeActivity(int[] start,int[] end){
        //存储结果
        List<Integer> result=new LinkedList<>();
        int endFlag=0;
        for(int i=0;i<=start.length-1;i++){
            if(start[i]>endFlag){
                endFlag=end[i];
                result.add(i);
            }
        }
        return result;
    }

    public static void main(String[] args){
        int[] start = {1, 3, 0, 5, 3, 5, 6, 8, 8, 2, 12};
        int[] end = {4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14};
        List<Integer> result=arrangeActivity(start,end);
        System.out.println(result);


    }
}
