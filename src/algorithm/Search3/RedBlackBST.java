package algorithm.Search3;

/**
 * 实现红黑二叉查找树
 * @param <Key>
 * @param <Value>
 */
public class RedBlackBST<Key extends Comparable<Key>, Value> {

    private Node root;

    private static final boolean RED=true;
    private static final boolean BLACK=false;

    private class Node{
        Key key;           //键值
        Value val;         //相关联的值
        Node left,right;   //左右子树
        int N;             //这棵子树中的结点总数
        boolean color;     //其父结点指向它的链接的颜色

        Node(Key key,Value val,int N,boolean color){
            this.key=key;
            this.val=val;
            this.N=N;
            this.color=color;
        }
    }

    /**
     * 判断当前节点是否是红色链接
     * @param x
     * @return
     */
    private boolean isRed(Node x){
        if(x==null) return BLACK;
        return x.color==RED;
    }

    /**
     * 计算当前节点的数量
     * @return
     */
    public int size(){
        return size(root);
    }
    private int size(Node x){
        if(x==null) return 0;
        else return x.N;
    }


    /**
     * 左旋转和右旋转
     * @param h
     * @return
     */
    Node rotateLeft(Node h){
       Node x=h.right;
       h.right=x.left;
       x.left=h;
       x.color=h.color;
       h.color=RED;
       x.N=h.N;
       h.N=1+size(h.left)+size(h.right);
       return x;
    }

    Node rotateRight(Node h){
        Node x=h.left;
        h.left=x.right;
        x.right=h;
        x.color=h.color;
        h.color=RED;
        x.N=h.N;
        h.N=1+size(h.left)+size(h.right);
        return x;
    }

    /**
     * 颜色转换
     * @param h
     */
    void flipColors(Node h){
        h.color=RED;
        h.left.color=BLACK;
        h.right.color=BLACK;
    }


    /**
     * 查询
     * @param key
     * @return
     */
    public Value get(Key key){
        return get(root,key);
    }
    private Value get(Node x,Key key){
        if(x==null) return null;
        int cmp=key.compareTo(x.key);
        if(cmp<0) return get(x.left,key);
        else if(cmp>0) return get(x.right,key);
        else return x.val;
    }


    /**
     * 插入操作
     * @param key
     * @param val
     */
    public void put(Key key,Value val){
        root=put(root,key,val);
        root.color=BLACK;
    }
    private Node put(Node h,Key key,Value val){
        if(h==null) return new Node(key,val,1,RED);    //标准插入，和父结点用红链接相连

        int cmp=key.compareTo(h.key);
        if(cmp<0) h.left=put(h.left,key,val);
        else if(cmp>0) h.right=put(h.right,key,val);
        else h.val=val;

        if(isRed(h.right) && !isRed(h.left)) h=rotateLeft(h);
        if(isRed(h.left) && isRed(h.left.left)) h=rotateRight(h);
        if(isRed(h.left) && isRed(h.right)) flipColors(h);

        h.N=1+size(h.left)+size(h.right);
        return h;
    }

    public static void main(String[] args){
        RedBlackBST<String,Integer> rbbst=new RedBlackBST();
        rbbst.put("S",1);
        rbbst.put("E",2);

        System.out.println(rbbst.get("S"));
    }
}
