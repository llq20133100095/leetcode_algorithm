package algorithm.graph4;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * 连通分量
 */
public class ConnectedComponent {
    public static int V;        //顶点数目
    public static int E;        //边数目
    public static ArrayList<Integer>[] adj;  //邻接表

    public static boolean[] marked;
    public static int[] id;         //标记顶点属于哪个连通分量
    public static int count=0;    //记录多少个连通分量

    /**
     * 邻接表
     * @param filename
     * @throws Exception
     */
    public ConnectedComponent(String filename) throws Exception{
        FileReader file=new FileReader(filename);
        BufferedReader br=new BufferedReader(file);
        String s=null;
        V=Integer.parseInt(br.readLine());
        E=Integer.parseInt(br.readLine());

        //初始化
        adj=(ArrayList<Integer>[]) new ArrayList[V];
        for(int i=0;i<adj.length;i++){
            adj[i]=new ArrayList<>();
        }

        while((s=br.readLine())!=null){
            String[] line=s.split(" ");
            int v=Integer.parseInt(line[0]);
            int w=Integer.parseInt(line[1]);

            //添加一条边
            adj[v].add(w);
            adj[w].add(v);
        }

    }

    /**
     * 调用dfs方法，并计算连通分量
     * @param adj
     */
    public void cc(ArrayList<Integer>[] adj){
        marked=new boolean[V];
        id=new int[V];
        for(int s=0;s<adj.length;s++){
            if(!marked[s]){
                count++;
                marked[s]=true;
                dfs(adj,s);
            }
        }

    }

    /**
     * 深度优先遍历
     * @param adj
     * @param s
     */
    public static void dfs(ArrayList<Integer>[] adj,int s){
        marked[s]=true;
        id[s]=count;
        Iterator<Integer> ite=adj[s].iterator();
        while(ite.hasNext()){
            int w=ite.next();
            if(!marked[w]){
                dfs(adj,w);
            }
        }

    }

    public static void main(String[] args) throws Exception{
        ConnectedComponent con=new ConnectedComponent("./src/algorithm/graph4/CCGraph.txt");

        System.out.println("邻接表");
        for(int i=0;i<con.adj.length;i++){
            System.out.print(i+":");
            Iterator<Integer> ite=adj[i].iterator();
            while(ite.hasNext()){
                System.out.print(" "+ite.next());
            }
            System.out.println();
        }

        System.out.println("连通分量");
        con.cc(con.adj);
        for(int i=1;i<count+1;i++){
            for(int j=0;j<id.length;j++){
                if(id[j]==i){
                    System.out.print(j+" ");
                }
            }
            System.out.println();
        }
    }
}
