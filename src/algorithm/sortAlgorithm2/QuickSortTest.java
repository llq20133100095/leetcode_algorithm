package algorithm.sortAlgorithm2;

/**
 * 快速排序
 * Created by llq on 2017/8/6.
 */
public class QuickSortTest {

    /**
     * 当v<w时，返回true
     * @param v
     * @param w
     * @return
     */
    private static boolean less(Comparable v,Comparable w){
        return v.compareTo(w)<0;
    }

    /**
     * 交换元素
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
     *切分元素
     * @return
     */
    private static int partition(Comparable[] data,int lo,int hi){
        int i=lo;
        int j=hi+1;     //左右扫描指针
        Comparable v=data[lo];  //切分元素

        while(true){
            //从左开始扫描，找到比v大于等于的元素
            while(less(data[++i],v)){
                if(i==hi) break;
            }
            //从右开始扫描，找到比v小于等于的元素
            while(less(v,data[--j])){
                if(j==lo) break;
            }
            if(i>=j) break;
            exch(data,i,j);
        }
        exch(data,lo,j);    //将v=a[j]放入正确的位置
        return j;
    }

    /**
     * 快速排序
     * @param data
     * @param lo
     * @param hi
     */
    public static void sort(Comparable[] data,int lo,int hi){
        if(hi<=lo) return;
        int j=partition(data,lo,hi);
        sort(data,lo,j-1);        //将左半部分排序
        sort(data,j+1,hi);        //将右半部分排序
    }

    public static void main(String[] args){
        String[] data=new String[]{"Q","U","I","C","K","S","O","R","T","E","X","A","M","P","L"};
        sort(data,0,data.length-1);
        for(String x:data){
            System.out.print(x+" ");
        }
    }
}
