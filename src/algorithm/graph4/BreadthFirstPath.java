package algorithm.graph4;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * 实现广度优先搜索
 */
public class BreadthFirstPath {

    public static int V;                //顶点的数目
    public static int E;                //边的数目
    public static ArrayList<Integer>[] adj; //邻接表

    public static boolean[] marked;       //标记已经遍历的顶点

    /**
     * 构建邻接表
     * @param filename
     * @throws Exception
     */
    public static void Graph(String filename) throws Exception{
        FileReader file=new FileReader(filename);
        BufferedReader br=new BufferedReader(file);
        String s=null;
        V=Integer.parseInt(br.readLine());
        E=Integer.parseInt(br.readLine());

        adj=(ArrayList<Integer>[]) new ArrayList[V];
        for(int i=0;i<V;i++){           //初始化链表
            adj[i]=new ArrayList<Integer>();
        }

        while ((s=br.readLine())!=null){
            //添加一条边
            String[] line=s.split(" ");
            int v=Integer.parseInt(line[0]);
            int w=Integer.parseInt(line[1]);
            adj[v].add(w);
            adj[w].add(v);
        }

        br.close();
        file.close();
    }

    /**
     * 广度优先算法
     * @param adj  邻接表
     * @param s    起始点
     */
    public static void bfs(ArrayList<Integer>[] adj,int s){
        Queue<Integer> queue=new LinkedBlockingQueue<>();
        queue.add(s);
        marked[s]=true;

        while(!queue.isEmpty()) {
            int v=queue.remove();
            System.out.print(v+"->");
            Iterator<Integer> i = adj[v].iterator();
            while (i.hasNext()) {
                int w = i.next();
                if (!marked[w]) {
                    marked[w]=true;
                    queue.add(w);
                }
            }
        }

    }

    public static void main(String[] args) throws Exception{
        Graph("./graph.txt");

        System.out.println("广度优先搜索:");
        marked=new boolean[V];
        bfs(adj,0);
        System.out.println();

    }

}
