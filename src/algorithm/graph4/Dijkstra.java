package algorithm.graph4;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.*;

/**
 * Dijkstra求最短路径问题
 */
public class Dijkstra {
    public int V;
    public int E;
    public ArrayList<Edge>[] adj;

    public boolean marked[];
    public Map<Integer,Double> pq;              //key：到当前节点，value：权重
    public String[] path;                        //存储路径

    public Dijkstra(String filename) throws Exception{
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

        //Dijkstra
        marked=new boolean[V];
        pq=new HashMap<>();
        for(int i=0;i<V;i++){       //赋值为正无穷
            pq.put(i,Double.POSITIVE_INFINITY);
        }


        //设定起点和起点的权值
        pq.put(0,0.0);
        path=new String[V];
        for(int i=0;i<V;i++){
            path[i]="";
        }
        visit(adj,0);

        //使用Dijkstra算法
        for(int count = 1;count <= V - 1;count++){
            int minKey=selectMin(pq);
            visit(adj,minKey);
        }
        //完善路径图
        for(int i=0;i<V;i++){
            path[i]=path[i]+i;
        }
    }

    /**
     * 选择pq中的最小值。
     * @param pq
     * @return
     */
    public int selectMin(Map<Integer,Double> pq){
        int minKey=0;      //存放最小值的key
        double min;     //存放最小的值

        Set<Integer> set=pq.keySet();
        Iterator<Integer> ite=set.iterator();
        min=Double.POSITIVE_INFINITY;

        //查找weight哪个是最小值
        while(ite.hasNext()){
            int key=ite.next();
            if(min>pq.get(key) && !marked[key]){
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
        Dijkstra dj=new Dijkstra("./src/algorithm/graph4/tinyEWD.txt");
//        Dijkstra dj=new Dijkstra("./src/algorithm/graph4/tinyEWDAG.txt");
        Set<Integer> set=dj.pq.keySet();
        Iterator<Integer> ite=set.iterator();

        //查找weight哪个是最小值
        while(ite.hasNext()){
            int key=ite.next();
            System.out.println(dj.pq.get(key));
        }
        System.out.println("路径图");
        for(String s:dj.path){
            System.out.println(s);
        }

    }
}
