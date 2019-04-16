package algorithm.sortAlgorithm2;

/**
 * 实现直接选择排序和直接插入排序，使用了comparable泛型
 * Created by llq on 2017/7/30.
 */
public class DirectSelectionAndInsertionSortTest {

    /**
     * 比较两个数的大小，当v<w时，返回true
     * @param v
     * @param w
     * @return
     */
    private static boolean less(Comparable v,Comparable w){
        //当v<w时，返回负数，则返回的是true。v>w时，返回正数
        return v.compareTo(w)<0;
    }

    /**
     * 交换两个数的位置
     * @param data
     * @param i
     * @param j
     */
    private static void exch(Comparable[] data,int i,int j){
        Comparable t=data[i];
        data[i]=data[j];
        data[j]=t;
    }

    /**
     * 实现直接选择排序
     * @param data
     */
    public static void directSelectionSort(Comparable[] data){
        for(int i=0;i<data.length;i++){
            //每次获取最小的值的位置
            int min=i;
            for(int j=i;j<data.length;j++){
                //判断data[j]<data[min]
                if(less(data[j],data[min])){
                    min=j;
                }
            }
            exch(data,i,min);
        }
    }

    /**
     * 实现直接插入排序
     */
    public static void directInsertionSort(Comparable[] data){
        for(int i=1;i<data.length;i++){
            //每一个data[j]跟左边的序列比较（跟data[j-1]比较），如果小于左边元素，则调换位置
            for(int j=i;j>0;j--){
                //如果比左边序列小，则调换位置
                //如果data[j]<data[j-1]，则为true
                if(less(data[j],data[j-1])){
                    exch(data,j,j-1);
                }
            }
        }
    }

    public static void main(String[] args){
        String[] data=new String[]{"U","p","p","b","r"};
        directInsertionSort(data);
        for(String x:data){
            System.out.print(x+" ");
        }
    }
}
