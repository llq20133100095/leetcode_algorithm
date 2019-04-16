package algorithm.graph4;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Queue;
import java.util.Stack;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * 有向图中计算强连通分量
 */
public class KosarajuSCC {
    public int V;                        //顶点数目
    public int E;                        //边数目
    public ArrayList<Integer>[] adj;     //邻接表
    public ArrayList<Integer>[] adjReverse;     //邻接表

    public boolean[] marked;

    public int count=0;
    public int[] id;    //记录顶点在哪个连通分量
    /**
     * 有向图邻接表
     * @param filename
     * @throws Exception
     */
    public KosarajuSCC(String filename) throws Exception{
        FileReader file=new FileReader(filename);
        BufferedReader br=new BufferedReader(file);
        String s=null;
        this.V=Integer.parseInt(br.readLine());
        this.E=Integer.parseInt(br.readLine());
        this.marked=new boolean[V];

        //初始化
        this.adj=(ArrayList<Integer>[]) new ArrayList[V];
        for(int i=0;i<this.adj.length;i++){
            this.adj[i]=new ArrayList<Integer>();
        }

        //创建邻接表
        while ((s=br.readLine())!=null){
            String[] line=s.split(" ");
            int v=Integer.parseInt(line[0]);
            int w=Integer.parseInt(line[1]);
            this.adj[v].add(w);
        }

        file.close();
        br.close();

        //计算强连通分量
        reverse(filename);
        id=new int[V];
        DepthFirstOrder dfo=new DepthFirstOrder(adjReverse);
        while(!dfo.reversePost.isEmpty()){
            int i=dfo.reversePost.pop();
            if(!marked[i]){
                dfs(adj,i);
                count++;
            }
            System.out.print(i+" ");
        }

    }

    /**
     * 翻转图
     * @param filename
     * @throws Exception
     */
    public void reverse(String filename) throws Exception{
        FileReader file=new FileReader(filename);
        BufferedReader br=new BufferedReader(file);
        String s=null;
        this.V=Integer.parseInt(br.readLine());
        this.E=Integer.parseInt(br.readLine());

        //初始化
        this.adjReverse=(ArrayList<Integer>[]) new ArrayList[this.V];
        for(int i=0;i<this.adjReverse.length;i++){
            this.adjReverse[i]=new ArrayList<Integer>();
        }

        //创建邻接表
        while ((s=br.readLine())!=null){
            String[] line=s.split(" ");
            int v=Integer.parseInt(line[0]);
            int w=Integer.parseInt(line[1]);
            this.adjReverse[w].add(v);
        }

        file.close();
        br.close();
    }

    /**
     * 深度优先搜索
     * @param adj
     * @param v
     */
    public void dfs(ArrayList<Integer>[] adj,int v){
        marked[v]=true;
        id[v]=count;
        Iterator<Integer> ite=adj[v].iterator();
        while(ite.hasNext()){
            int s=ite.next();
            if(!marked[s]){
                dfs(adj,s);
            }
        }
    }

    public static void main(String[] args) throws Exception{
        KosarajuSCC kscc=new KosarajuSCC("./src/algorithm/graph4/KSCC.txt");

        System.out.println("邻接表");
        for(int i=0;i<kscc.adj.length;i++){
            System.out.print(i+":");
            Iterator<Integer> ite=kscc.adj[i].iterator();
            while(ite.hasNext()){
                System.out.print(ite.next()+" ");
            }
            System.out.println();
        }

        //计算强连通分量
        for(int i=0;i<kscc.count+1;i++){
            for(int j=0;j<kscc.id.length;j++){
                if(i==kscc.id[j]){
                    System.out.print(j+" ");
                }
            }
            System.out.println();
        }

    }
}

/**
 * 实现逆后序排序
 */
class DepthFirstOrder{
    public Queue<Integer> pre;
    public Stack<Integer> reversePost; //所有顶点的逆后序排序
    public boolean[] marked;  //标记顶点已经被遍历

    public DepthFirstOrder(ArrayList<Integer>[] adj){
        this.pre=new LinkedBlockingQueue<>();
        this.reversePost=new Stack<>();
        this.marked=new boolean[adj.length];
        for(int i=0;i<adj.length;i++){
            if(!this.marked[i]){
                dfs(adj,i);
            }

        }
    }


    /**
     * 深度优先搜索：逆后序
     * @param adj
     * @param v
     */
    public void dfs(ArrayList<Integer>[] adj,int v){
        pre.add(v);
        marked[v]=true;
        Iterator<Integer> ite=adj[v].iterator();
        while(ite.hasNext()){
            int s=ite.next();
            if(!marked[s]){
                dfs(adj,s);
            }
        }

        reversePost.push(v);

    }
}
