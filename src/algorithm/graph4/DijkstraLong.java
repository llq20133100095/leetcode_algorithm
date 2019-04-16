package algorithm.graph4;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.*;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * 实现最长路径
 */
public class DijkstraLong {
    public int V;
    public int E;
    public ArrayList<Edge>[] adj;

    public boolean marked[];
    public Map<Integer,Double> pq;              //key：到当前节点，value：权重
    public String[] path;                        //存储路径

    public DijkstraLong(String filename) throws Exception{
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
        }

        file.close();
        br.close();

        TuoPu dfo=new TuoPu(adj);
        while(!dfo.reversePost.isEmpty()){
            int i=dfo.reversePost.pop();
            System.out.print(i+" ");
        }

        //使用dijkstra算法
        for(int i=0;i<V-1;i++){
            int minKey=selectMin(pq);
            visit(adj,minKey);
        }

    }

    /**
     * 选出最小值的顶点
     * @param pq
     */
    public int selectMin(Map<Integer,Double> pq){
        int minKey=0;
        double min=0.0;

        Set<Integer> set=pq.keySet();
        Iterator<Integer> ite=set.iterator();
        while(ite.hasNext()){
            int key=ite.next();
            if(!marked[key] && min>pq.get(key)){
                minKey=key;
                min=pq.get(key);
            }

        }
        return minKey;
    }

    public void visit(ArrayList<Edge>[] adj,int v){
        marked[v]=true;
        Iterator<Edge> ite=adj[v].iterator();
        while(ite.hasNext()){
            Edge e=ite.next();
            int w=e.other(v);
            if(!marked[w]){
                if(pq.get(w)>pq.get(v)+e.weight){
                    pq.put(w,pq.get(v)+e.weight);
                    path[w]=path[v]+v+"-->";
                }
            }
        }
    }

    public static void main(String[] args) throws Exception{
        DijkstraLong dj=new DijkstraLong("./src/algorithm/graph4/tinyEWDAG.txt");


    }
}

/**
 * 实现逆后序排序
 */
class TuoPu{
    public Queue<Integer> pre;
    public Stack<Integer> reversePost; //所有顶点的逆后序排序
    public boolean[] marked;  //标记顶点已经被遍历

    public TuoPu(ArrayList<Edge>[] adj){
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
    public void dfs(ArrayList<Edge>[] adj,int v){
        pre.add(v);
        marked[v]=true;
        Iterator<Edge> ite=adj[v].iterator();
        while(ite.hasNext()){
            Edge e=ite.next();
            int w=e.other(v);
            if(!marked[w]){
                dfs(adj,w);
            }
        }

        reversePost.push(v);

    }
}
