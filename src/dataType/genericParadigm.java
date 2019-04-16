package dataType;

/**
 * 定义泛型结构
 * Created by llq on 2017/7/23.
 */
public class genericParadigm {

    public static void main(String[] args){
        Box<String> name=new Box<String>("corn");
        System.out.println(name.getData());

    }
}

class Box<T>{
    private T data;
    public Box(){

    }
    public Box(T data){
        this.data=data;
    }
    public T getData(){
        return data;
    }
}
