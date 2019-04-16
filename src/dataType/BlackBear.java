package dataType;

/**
 * 黑熊类:实现接口方法
 * Created by llq on 2017/7/19.
 */
public class BlackBear implements OnEarthInterface {
    private int bearSpeed;
    public void earthMove(){    //实现接口方法
        bearSpeed=earthSpeed*2;
    }

    public static void main(String[] args){
        BlackBear a=new BlackBear();
        a.earthMove();
        System.out.println(a.bearSpeed);
    }
}
