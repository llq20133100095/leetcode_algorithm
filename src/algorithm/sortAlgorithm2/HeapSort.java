package algorithm.sortAlgorithm2;

/**
 * Created by llq on 2018/6/15.
 * 实现堆排序
 */
public class HeapSort {
    /**
     * 比较两个值的大小
     * @param data
     * @param i
     * @param j
     * @return
     */
    private static boolean less(Comparable[] data,int i,int j){
        return data[i].compareTo(data[j])<0;
    }

    private static void exch(Comparable[] data,int i,int j){
        Comparable t=data[i];
        data[i]=data[j];
        data[j]=t;
    }

    /**
     * 下沉：当孩子结点比双亲结点要大时，则替换位置。
     * @param data
     * @param k
     */
    private static void sink(Comparable[] data,int k,int lo){
        while(2*k<=lo){
            int j=2*k;
            //取出两个孩子结点中的最大值(保证有两个孩子结点)
            if(j<lo && less(data,j,j+1)){
                j++;
            }
            if(less(data,k,j)){
                exch(data,k,j);
                k=j;
            }else{
                break;
            }
        }
    }

    /**
     * 堆排序
     * @param data
     */
    public static void sort(Comparable[] data){
        int N=data.length-1;
        for(int k=N/2;k>=1;k--){
            sink(data,k,N);
        }
        for(int i=N-1;i>=1;i--){
            exch(data,1,i+1);  //取出最大的元素，并放在堆尾
            sink(data,1,i);    //比较剩下的元素，恢复堆有序
        }
    }
    public static void main(String[] args){
        Comparable[] data=new Comparable[12];
        data[1]="S";data[2]="O";data[3]="R";data[4]="T";data[5]="E";data[6]="X";data[7]="A";data[8]="M";data[9]="P";
        data[10]="L";data[11]="E";

        sort(data);

        for(Comparable i:data){
            System.out.println(i);
        }
    }

}
