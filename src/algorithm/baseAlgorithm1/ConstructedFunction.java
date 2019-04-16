package algorithm.baseAlgorithm1;

/**
 * Created by llq on 2018/5/29.
 * 构造函数
 */
interface parent{
    int feature1=0;
    int feature2=0;
}

public class ConstructedFunction implements parent{

//    int feature1;
//    //构造函数初始化一些值
//    public constructedFunction(){
//        this.feature1=1;
//    }

    public static void main(String[] args){
        ConstructedFunction con=new ConstructedFunction();
        System.out.println(con.feature1);
    }
}


