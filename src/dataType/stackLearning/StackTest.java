package dataType.stackLearning;

import java.util.Stack;

/**
 * Stack类
 * 栈：桶型或箱型数据类型，后进先出，相对堆Heap为二叉树类型，可以快速定位并操作
 * Stack<E>，支持泛型
 * public class Stack<E> extends Vector<E>
 * Stack的方法调用的Vector的方法，被synchronized修饰，为线程安全(Vector也是)
 * Stack methods：
 * push : 把项压入堆栈顶部 ，并作为此函数的值返回该对象
 * pop : 移除堆栈顶部的对象，并作为此函数的值返回该对象
 * peek : 查看堆栈顶部的对象，，并作为此函数的值返回该对象，但不从堆栈中移除它
 * empty : 测试堆栈是否为空
 * search : 返回对象在堆栈中的位置，以 1 为基数
 * */
public class StackTest {

    public static void main(String[] args){
        Stack<Integer> stack =new Stack<Integer>();
        //此处将1到5压入栈中
        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.push(4);
        stack.push(5);
        System.out.println("将1到5按顺序压入栈中后为："+stack);

        //search : 返回对象在堆栈中的位置，以 1 为基数，参数：search(Object o) ，返回值int
        int oStack = stack.search(4);
        System.out.println("查找栈stack中对象4的位置elementId为 : "+oStack);

        //pop : 移除堆栈顶部的对象，并作为此函数的值返回该对象，返回值泛型指定的类型
        int oRemove = stack.pop();
        System.out.println("移除stack栈顶的元素为 : "+oRemove);
        System.out.println("pop操作移除stack栈顶元素后为 : "+stack);
    }
}
