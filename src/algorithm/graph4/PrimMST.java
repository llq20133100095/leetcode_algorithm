package algorithm.graph4;


import java.io.BufferedReader;
import java.io.FileReader;
import java.util.*;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Prim即时算法
 */
public class PrimMST {
    public int V;
    public int E;
    public ArrayList<Edge>[] adj;

    public boolean[] marked;        //标记哪些顶点被遍历
    public Queue<Edge> mst;         //存放最小生成树的边
    public Map<Integer,Edge> pq;    //存放有效的横切边

    public PrimMST(String filename) throws Exception{
        BufferedReader br=new BufferedReader(new FileReader(filename));
        V=Integer.parseInt(br.readLine());
        E=Integer.parseInt(br.readLine());
        String s=null;

        //初始化
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
        br.close();

        //Prim算法
        marked=new boolean[V];
        pq=new HashMap<>();
        for(int i=0;i<V;i++){       //首先设置0->其他顶点的权值为负无穷
            pq.put(i,new Edge(0,i,Double.POSITIVE_INFINITY));
        }
        mst=new LinkedBlockingQueue<>();
        visit(adj,0);
        while (!pq.isEmpty()){
            int minKey=selectMin(pq);
            Edge e=pq.remove(minKey);       //最小生成树的一条边
            int v=e.either();
            int w=e.other(v);
            if(e.weight!=Double.POSITIVE_INFINITY) mst.add(e);                     //保存最小生成树的边
            if(!marked[v]) visit(adj,v);
            if(!marked[w]) visit(adj,w);
        }
    }

    /**
     * 选择pq中的最小值。
     * @param pq
     * @return
     */
    public int selectMin(Map<Integer,Edge> pq){
        int minKey=0;      //存放最小值的key
        double min;     //存放最小的值

        Set<Integer> set=pq.keySet();
        Iterator<Integer> ite=set.iterator();
        min=pq.get(ite.next()).weight;

        //查找weight哪个是最小值
        while(ite.hasNext()){
            int key=ite.next();
            if(min>pq.get(key).weight){
                minKey=key;
                min=pq.get(key).weight;
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
                //判断新的边 比 原来的边的权值要小
                if(e.weight<pq.get(w).weight){
                    pq.put(w,e);        //覆盖原来的权值
                }

            }
        }
    }

    public static void main(String[] args) throws Exception{
        PrimMST pmst=new PrimMST("./src/algorithm/graph4/primGraph.txt");

        System.out.println("Prim：");
        while(!pmst.mst.isEmpty()){
            Edge e=pmst.mst.remove();
            System.out.print(e.v+"->"+e.w+"("+e.weight+")"+" ");
        }
    }

}

