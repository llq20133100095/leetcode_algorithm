package algorithm.graph4;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.*;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * 延时Prim算法：最小生成树
 */
public class LazyPrimMST {
    public int V;
    public int E;
    public ArrayList<Edge>[] adj;

    public boolean[] marked;        //最小生成树顶点
    public Queue<Edge> mst;         //最小生成树的边
    public PriorityQueue<Edge> pq;  //横切边（包括失效的边）=>小根堆,可以返回最小值

    public LazyPrimMST(String filename) throws Exception{
        FileReader file=new FileReader(filename);
        BufferedReader br=new BufferedReader(file);
        String s=null;
        V=Integer.parseInt(br.readLine());
        E=Integer.parseInt(br.readLine());

        //初始化
        adj=new ArrayList[V];
        for(int i=0;i<adj.length;i++){
            adj[i]=new ArrayList<>();
        }

        //构建邻接表
        while((s=br.readLine())!=null){
            String[] line=s.split(" ");
            int v=Integer.parseInt(line[0]);
            int w=Integer.parseInt(line[1]);
            double weight=Double.parseDouble(line[2]);
            Edge edge=new Edge(v,w,weight);
            adj[v].add(edge);
            adj[w].add(edge);
        }

        file.close();
        br.close();

        //Prim算法
        marked=new boolean[V];
        mst=new LinkedBlockingQueue<>();
        pq=new PriorityQueue<>();
        visit(adj,0);
        while(!pq.isEmpty()){
            //把最小权重的边拿出来。
            Edge e=pq.poll();

            int v=e.either();
            int w=e.other(v);
            if(marked[v]&&marked[w]) continue; //跳过失效的边
            mst.add(e);
            if(!marked[v]) visit(adj,v);
            if(!marked[w]) visit(adj,w);
        }

    }

    /**
     * 和深度优先搜索很像，用来把附近的边加入到pq中。
     * @param adj
     * @param v
     */
    private void visit(ArrayList<Edge>[] adj,int v){
        marked[v]=true;
        Iterator<Edge> ite=adj[v].iterator();
        while(ite.hasNext()){
            Edge e=ite.next();
            if(!marked[e.other(v)]){
                pq.add(e);
            }
        }
    }

    public static void main(String[] args) throws Exception{
        LazyPrimMST lpmst=new LazyPrimMST("./src/algorithm/graph4/primGraph.txt");
        System.out.println("邻接表：");
        for(int i=0;i<lpmst.adj.length;i++){
            System.out.print(i+":");
            Iterator<Edge> ite=lpmst.adj[i].iterator();
            while(ite.hasNext()){
                Edge e=ite.next();
                System.out.print(e.v+"->"+e.w+"("+e.weight+")"+" ");
            }
            System.out.println();
        }

        System.out.println("Prim：");
        while(!lpmst.mst.isEmpty()){
            Edge e=lpmst.mst.remove();
            System.out.print(e.v+"->"+e.w+"("+e.weight+")"+" ");
        }
    }
}

/**
 * 实现边的数据类型
 */
class Edge implements Comparable<Edge>{
    public int v;       //两个顶点
    public int w;
    public double weight;  //权重

    public Edge(int v,int w,double weight){
        this.v=v;
        this.w=w;
        this.weight=weight;
    }

    //返回两个顶点
    public int either(){
        return v;
    }
    public int other(int vertex){
        if(vertex==v) return w;
        else if(vertex==w) return v;
        else throw new RuntimeException("Inconsistent edge");
    }

    /**
     * 用来进行比较。把最小的权重排在小根堆的前面。
     * @param that
     * @return
     */
    public int compareTo(Edge that){
        if(this.weight<that.weight) return -1;
        else if(this.weight>that.weight) return +1;
        else return 0;
    }
}

