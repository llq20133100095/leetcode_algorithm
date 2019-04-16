package dataType.queueLearning;

import java.util.Iterator;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * 队列练习操作：添加操作和iter迭代操作
 * Created by llq on 2017/7/24.
 */
public class QueueProgram {
    public static void main(String[] args){
        Queue<String> queue=new LinkedBlockingDeque<String>();
        for(int i=0;i<=10;i++){
            queue.offer(i+"");
        }
        Iterator<String> iter=queue.iterator();
        while(iter.hasNext()){
            System.out.println(iter.next());
        }
    }

}
