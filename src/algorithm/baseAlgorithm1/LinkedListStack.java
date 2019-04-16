package algorithm.baseAlgorithm1;

/**
 * Created by llq on 2018/6/15.
 * 利用链表linked list实现下压栈
 */
public class LinkedListStack<Item> {
    private Node first; //栈顶（最近添加的元素）
    private int N;
    private class Node{
        //定义了结点的嵌套类
        Item item;
        Node next;
    }

    public boolean isEmpty(){
        return first==null;
    }

    public int size(){
        return N;
    }

    public void push(Item item){
        //向栈顶添加元素
        Node oldfirst=first;
        first=new Node();
        first.item=item;
        first.next=oldfirst;
        N++;
    }

    public Item pop(){
        //从栈顶删除元素
        Item item=first.item;
        first=first.next;
        N--;
        return item;
    }

    public static void main(String[] args){
        LinkedListStack<String> s=new LinkedListStack<String>();
        s.push("S");
        s.push("A");
        System.out.println(s.pop());
    }
}
