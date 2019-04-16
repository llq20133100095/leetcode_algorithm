package algorithm.Search3;

/**
 * 二叉查找树
 */
public class BST<Key extends Comparable<Key>, Value> {

    private Node root;

    /**
     * 定义结点
     */
    private class Node{
        private Key key;           //键
        private Value value;       //值
        private Node left,right;   //指向子树的链接
        private int N;             //以该结点为根的子树中的结点总数

        public Node(Key key,Value value,int N){
            this.key=key;
            this.value=value;
            this.N=N;
        }
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
        else return x.value;
    }

    /**
     * 更新和插入数据
     * @param key
     * @param value
     */
    public void put(Key key,Value value){
        root=put(root,key,value);
    }
    private Node put(Node x,Key key,Value value){
        if(x==null) return new Node(key,value,1);
        int cmp=key.compareTo(x.key);
        if(cmp<0) x.left=put(x.left,key,value);
        else if(cmp>0) x.right=put(x.right,key,value);
        else x.value=value;
        x.N=size(x.left)+size(x.right)+1;
        return x;
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
     * 按顺序打印二叉查找树中的所有键
     * @param x
     */
    public void print(Node x){
        if(x==null) return;
        print(x.left);
        System.out.println(x.key);
        print(x.right);
    }

    /**
     * 返回最小值
     * @return
     */
    public Key min(){
        return min(root).key;
    }
    private Node min(Node x){
        if(x.left==null) return x;
        return min(x.left);
    }


    /**
     * 向下取整
     * @param key
     * @return
     */
    public Key floor(Key key){
        Node x=floor(root,key);
        if(x==null)return null;
        return x.key;
    }
    private Node floor(Node x,Key key){
        if(x==null) return null;
        int cmp=key.compareTo(x.key);
        if(cmp==0) return x;
        if(cmp<0) return floor(x.left,key);
        Node t=floor(x.right,key);
        if(t!=null) return t;
        else return x;
    }


    /**
     * 删除最小值
     */
    public void deleteMin(){
        root=deleteMin(root);
    }
    private Node deleteMin(Node x){
        if(x.left==null) return x.right;
        x.left=deleteMin(x.left);
        x.N=size(x.left)+size(x.right)+1;
        return x;
    }


    /**
     * 删除任意节点
     * @param key
     */
    public void delete(Key key){
        root=delete(root,key);
    }
    private Node delete(Node x,Key key){
        if(x==null) return null;
        int cmp=key.compareTo(x.key);
        if(cmp<0) x.left=delete(x.left,key);
        else if(cmp>0) x.right=delete(x.right,key);
        else{
            if(x.right==null) return x.left;
            if(x.left==null) return x.right;
            Node t=x;
            x=min(t.right);  //找到t节点的右子树的最左边的节点（t的后继节点）
            x.right=deleteMin(t.right);  //删除t的后继结点后，此时t的右子树
            x.left=t.left;
        }
        x.N=size(x.left)+size(x.right)+1;
        return x;
    }

    public static void main(String[] args){
        BST<String,Integer> bst=new BST<>();
        bst.put("A",1);
        bst.put("C",2);
        bst.put("E",3);
        bst.put("H",4);
        bst.put("M",5);
        bst.put("R",6);
        bst.put("S",7);
        bst.put("X",8);

        bst.delete("H");
        bst.print(bst.root);
    }
}
