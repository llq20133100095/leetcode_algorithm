package algorithm.sortAlgorithm2;

/**
 * 希尔排序
 * Created by llq on 2017/7/31.
 */
public class ShellSortTest {

    /**
     * 比较两个元素的大小
     * @param v
     * @param w
     * @return
     */
    private static boolean less(Comparable v,Comparable w){
        //如果v<w，则返回true
        return v.compareTo(w)<0;
    }

    /**
     * 交换两个元素的位置
     * @param a
     * @param i
     * @param j
     */
    private static void exch(Comparable[] data,int i,int j){
        Comparable t=data[i];
        data[i]=data[j];
        data[j]=t;
    }

    /**
     * 实现希尔序列
     * @param data
     */
    public static void shellSort(Comparable[] data){
        int N=data.length;
        int h=1;
        while(h<N/3){
            //希尔序列：1，4,13,40,121。。。。。
            h=h*3+1;
        }
        while(h>=1){
            for(int i=0;i<N;i++){
                for(int j=i;j>h && less(data[j],data[j-h]);j-=h){
                    exch(data,j,j-h);
                }
            }
            h=h/3;
        }
    }

    public static void main(String[] args){
        Double[] data=new Double[]{12.0,56.1,23.1,78.2,90.6,78.1,45.7,23.10,33.12,189.45,56.13,24.56,25.3,89.4};
        shellSort(data);
        for(double x:data){
            System.out.print(x+" ");
        }
    }
}
