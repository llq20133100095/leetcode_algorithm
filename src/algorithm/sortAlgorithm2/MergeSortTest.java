package algorithm.sortAlgorithm2;

/**
 * 归并排序
 * Created by llq on 2017/7/31.
 */
public class MergeSortTest {

    private static Comparable[] aux;//归并所需的辅助数组

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
     * 原地归并的抽象方法
     * @param data
     * @param lo
     * @param mid
     * @param hi
     */
    public static void merge(Comparable[] data,int lo,int mid,int hi){
        //将a[lo....mid]和a[mid+1....hi]归并
        int i=lo;
        int j=mid+1;
        //复制元素到一个新的数组中
        for(int k=lo;k<=hi;k++){
            aux[k]=data[k];
        }
        for(int k=lo;k<=hi;k++){
            if(i>mid) data[k]=aux[j++];     //左半边用尽，取右半边的元素
            else if(j>hi) data[k]=aux[i++]; //右半边用尽，取左半边的元素
            else if(less(aux[j],aux[i])) data[k]=aux[j++];  //右半边的当前元素小于左半边的当前元素
            else data[k]=aux[i++];  //右半边的当前元素大于等于左半边的当前元素
        }

    }

    /**
     * 自顶向下的归并排序
     */
    public static void topDownSort(Comparable[] data){
        aux=new Comparable[data.length];
        topDownSort(data,0,data.length-1);
    }

    private static void topDownSort(Comparable[] data,int lo,int hi){
        if(lo>=hi) return;
        int mid=(lo+hi)/2;
        topDownSort(data,lo,mid);       //左半边元素，a[lo.....mid]
        topDownSort(data,mid+1,hi);     //a[mid+1.....hi]
        merge(data, lo, mid, hi);
    }

    /**
     * 自底向上的归并排序
     * @param data
     */
    public static void downTopSort(Comparable[] data){
        int N=data.length;
        aux=new Comparable[N];
        for(int sz=1;sz<N;sz=sz+sz){                //sz子数组大小
            for(int lo=0;lo<N-sz;lo+=sz+sz){        //lo:子数组索引
                merge(data,lo,lo+sz-1,Math.min(lo+sz-1+sz,N-1));    //归并时可能会超出最左边的索引
            }
        }
    }
    public static void main(String[] args){
        String[] data=new String[]{"M","E","R","G","E","S","O","R","T","E","X","A","M","P","L","E"};
        downTopSort(data);
        for(String x:data){
            System.out.print(x+" ");
        }
    }
}
