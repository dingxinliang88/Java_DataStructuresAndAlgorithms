package dateStructures.graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

/**
 * @author Muffin_Head
 * @version 1.8.0_131
 */
public class Graph {
    /**
     * 存储顶点的集合
     */
    private ArrayList<String> vertexList;

    /**
     * 存储图对应的邻接矩阵
     */
    private int[][] edges;

    /**
     * 表示边的数目
     */
    private int numOfEdges;

    /**
     * 记录某个节点是否已经被访问
     */
    private boolean[] isvisited;

    public Graph(int numOfVertex) {
        vertexList = new ArrayList<>(numOfVertex);
        edges = new int[numOfVertex][numOfVertex];
        isvisited = new boolean[numOfVertex];
    }

    /**
     * 插入节点
     *
     * @param vertex 待插入节点
     */
    public void insertVertex(String vertex) {
        vertexList.add(vertex);
    }

    /**
     * 插入边
     *
     * @param v1     第一个顶点对应的下标
     * @param v2     第二个顶点对应的下标
     * @param weight 两个顶点之间的权重
     */
    public void insertEdge(int v1, int v2, int weight) {
        edges[v1][v2] = edges[v2][v1] = weight;
        numOfEdges++;
    }

    /**
     * 得到顶点的个数
     *
     * @return int
     */
    public int getNumOfVertex() {
        return vertexList.size();
    }

    /**
     * 得到边的条数
     *
     * @return int
     */
    public int getNumOfEdges() {
        return numOfEdges;
    }

    /**
     * 获得下标对应的数据
     *
     * @param index 下标
     * @return String
     */
    public String getValueByIndex(int index) {
        return vertexList.get(index);
    }

    /**
     * 获得v1和v2之间的权重
     *
     * @param v1 顶点对应的下标
     * @param v2 顶点对应的下标
     * @return int
     */
    public int getWeight(int v1, int v2) {
        return edges[v1][v2];
    }

    /**
     * 显示图对应的邻接矩阵
     */
    public void showGragh() {
        for (int[] edge : edges) {
            System.out.println(Arrays.toString(edge));
        }
    }

    /**
     * 找到下标为index节点的第一个邻接节点
     *
     * @param index 初始下标
     * @return 邻接节点下标 || -1
     */
    public int getFirstNeighbor(int index) {
        for (int i = 0; i < getNumOfVertex(); i++) {
            if (edges[index][i] > 0) {
                return i;
            }
        }
        return -1;
    }

    /**
     * 找到以v2为上一个邻接节点的v1节点的下一个邻接节点
     *
     * @param v1 起始节点的下标
     * @param v2 起始节点的上一个邻接节点
     * @return 下一个邻接节点下标 || -1
     */
    public int getNextNeighbor(int v1, int v2) {
        for (int i = v2 + 1; i < getNumOfVertex(); i++) {
            if (edges[v1][i] > 0) {
                return i;
            }
        }
        return -1;
    }

    /**
     * 深度优先遍历算法
     *
     * @param isvisited 记录节点是否被访问
     * @param index     遍历的起始节点下标
     */
    private void dfs(boolean[] isvisited, int index) {
        //访问起始节点
        System.out.print(getValueByIndex(index) + "->");
        //设置已被访问
        isvisited[index] = true;
        //找到邻接节点
        int w = getFirstNeighbor(index);
        while (w != -1) {//邻接节点存在
            if (!isvisited[w]) {  //邻接节点尚未被访问
                dfs(isvisited, w);
            }
            w = getNextNeighbor(index, w);//被访问过，找下一个邻接节点
        }
    }

    /**
     * 重载dfs()，遍历全部节点
     */
    public void dfs() {
        isvisited = new boolean[getNumOfVertex()];
        for (int i = 0; i < getNumOfVertex(); i++) {
            if (!isvisited[i]) {
                dfs(isvisited, i);
            }
        }
    }

    /**
     * 广度遍历优先算法
     *
     * @param isvisited 记录节点是否被访问
     * @param index     遍历的起始节点下标
     */
    private void bfs(boolean[] isvisited, int index) {
        int u;//记录队列头节点的下标
        int w;//记录邻接节点

        LinkedList<Integer> queue = new LinkedList<>();//记录节点访问顺序

        //输出节点信息
        System.out.print(getValueByIndex(index) + "->");
        isvisited[index] = true;
        queue.addLast(index);

        while (!queue.isEmpty()) {
            u = queue.removeFirst();//取出队列第一个节点信息
            w = getFirstNeighbor(u);
            while (w != -1) {//邻接节点存在
                if (!isvisited[w]) {//邻接节点尚未被访问
                    System.out.print(getValueByIndex(w) + "->");
                    isvisited[w] = true;
                    queue.addLast(w);
                }
                w = getNextNeighbor(u, w);//得到下一个邻接节点
            }
        }
    }

    /**
     * 重载广度优先算法，遍历所有节点
     */
    public void bfs() {
        isvisited = new boolean[getNumOfVertex()];
        for (int i = 0; i < getNumOfVertex(); i++) {
            if (!isvisited[i]) {
                dfs(isvisited, i);
            }
        }
    }
}
