package algorithm.dijkstra;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Muffin_Head
 * @version 1.8.0_131
 */
public class Graph {
    private static final int N = 65535;
    private char[] vertexs;//顶点集合
    private int[][] weight;//顶点间权重
    private VisitedVertex visitedVertex;//已经被访问顶点的集合
    private HashMap<String,Integer> res = new HashMap<>();

    public Graph(char[] vertexs, int[][] weight) {
        this.vertexs = vertexs;
        this.weight = weight;
    }


    /**
     * 显示图
     */
    public void showGraph(){
        for (int[] rows : weight) {
            System.out.println(Arrays.toString(rows));
        }
    }

    /**
     * 更新index顶点到周围顶点的距离，并更新周围顶点的前驱顶点
     * @param index 顶点下标
     */
    public void update(int index){
        int length = 0;//记录距离
        //遍历weight[index]行
        for (int i = 0; i < weight[index].length; i++) {
            //出发顶点到index顶点的距离 + 从index顶点到i顶点的距离的和
            length = visitedVertex.getDistence(index) + weight[index][i];
            //i顶点尚未被访问 && length < 出发顶点到i顶点的距离
            if(!visitedVertex.isVisitedByIndex(i)
                    && length < visitedVertex.getDistence(i)){
                visitedVertex.updatePre(i,index);//更新i顶点的前驱节点为index
                visitedVertex.updateDistence(length,i);//更新出发顶点到i顶点的距离
                String str = "<" + vertexs[index] + "->" + vertexs[i] + ">";
                res.put(str, weight[index][i]);
            }
        }
    }

    /**
     * 迪杰斯特拉算法实现
     * @param index 出发顶点
     */
    public void dijkstraAlgorithm(int index){
        visitedVertex = new VisitedVertex(vertexs.length,index);
        update(index);
        for (int i = 1;i < vertexs.length;i++){
            index = visitedVertex.updateInitialVertex();//更新新的访问顶点
            update(index);
        }
    }

    /**
     * 显示路径结果
     */
    public void show(){
        System.out.println("需要连接的路径");
        for (Map.Entry<String, Integer> entry : res.entrySet()) {
            System.out.println(entry.getKey() + "-->" + entry.getValue());
        }
        int[] distence = visitedVertex.getDistence();
        int initialIndex = visitedVertex.getInitialIndex();
        int count = 0;
        System.out.println("起始顶点到各个顶点的最短距离：");
        for (int dis : distence) {
            if(dis != N){
                System.out.println("<"+ vertexs[initialIndex] +
                        "->" + vertexs[count] +
                        "> -->" + dis);
            } else {
                System.out.println("<"+ vertexs[initialIndex] +
                        "->" + vertexs[count] +
                        "> --> N" );
            }
            count++;
        }

    }
}
