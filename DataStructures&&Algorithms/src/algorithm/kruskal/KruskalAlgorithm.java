package algorithm.kruskal;

import java.util.Arrays;

/**
 * @author Muffin_Head
 * @version 1.8.0_131
 */
public class KruskalAlgorithm {
    private int edgeNum;//边的数目
    private char[] vertexs;//存放顶点数据
    private int[][] weight;//存放顶点之间的权重
    private static final int INF = Integer.MAX_VALUE;

    public KruskalAlgorithm(char[] vertexs, int[][] weight) {
        int length = vertexs.length;

        //初始化数组信息
        this.vertexs = new char[length];
        System.arraycopy(vertexs, 0, this.vertexs, 0, length);
        this.weight = new int[length][length];
        System.arraycopy(weight, 0, this.weight, 0, length);

        //统计边的数目
        for (int i = 0; i < weight.length; i++) {
            for (int j = i + 1; j < weight[0].length; j++) {
                if (weight[i][j] != INF) {
                    edgeNum++;
                }
            }
        }
    }

    /**
     * Kruskal算法
     */
    public void kruskalAlgorithm() {
        int index = 0;
        int sumOfWeight = 0;
        int[] ends = new int[edgeNum];//存放顶点的终点下标
        EData[] res = new EData[edgeNum];//存放结果

        EData[] edges = getEdges();
        //排序
        sortEdges(edges);
        System.out.println(Arrays.toString(edges));
        for (int i = 0; i < edgeNum; i++) {
            //获取当前边的两个顶点
            int startVertex = getPosition(edges[i].getStart());
            int endVertex = getPosition(edges[i].getEnd());

            //获取两个顶点的终点下标
            int endOfStartVertex = getEnd(ends, startVertex);
            int endOfEndVertex = getEnd(ends, endVertex);

            if (endOfStartVertex != endOfEndVertex) {//没有构成回路
                ends[endOfStartVertex] = endOfEndVertex;
                res[index++] = edges[i];
                sumOfWeight += edges[i].getWeight();
            }
        }
        System.out.println("最小生成树为：");
        for (EData result : res) {
            if(result!=null){
                System.out.println(result);
            }
        }
        System.out.println("路径总长度：" + sumOfWeight);
        System.out.println("ends = " + Arrays.toString(ends));
    }

    /**
     * 打印邻接矩阵
     */
    public void print() {
        for (int[] cols : weight) {
            for (int item : cols) {
                System.out.printf("%12d\t", item);
            }
            System.out.println();
        }
    }

    /**
     * 对边进行排序
     *
     * @param edges 边的集合
     */
    public void sortEdges(EData[] edges) {
        boolean flag = false;//用于优化
        for (int i = 0; i < edges.length - 1; i++) {
            for (int j = 0; j < edges.length - 1 - i; j++) {
                if (edges[j].getWeight() > edges[j + 1].getWeight()) {
                    flag = true;
                    EData temp = edges[j];
                    edges[j] = edges[j + 1];
                    edges[j + 1] = temp;
                }
            }
            if (!flag) {//未进行排序
                break;
            }
            flag = false;//重置flage,进行下一轮判断
        }
    }

    /**
     * @return 边的集合
     */
    public EData[] getEdges() {
        int index = 0;
        EData[] edges = new EData[edgeNum];
        for (int i = 0; i < vertexs.length; i++) {
            for (int j = i + 1; j < vertexs.length; j++) {
                if (weight[i][j] != INF) {
                    edges[index++] = new EData(vertexs[i], vertexs[j], weight[i][j]);
                }
            }
        }
        return edges;
    }

    /**
     * 获取顶点的下标
     *
     * @param vertex 顶点的值
     * @return 顶点下标 || -1
     */
    public int getPosition(char vertex) {
        for (int i = 0; i < vertexs.length; i++) {
            if (vertexs[i] == vertex) {
                return i;
            }
        }
        return -1;
    }

    /**
     * 获取顶点的终点下标
     *
     * @param ends 记录各个顶点的终点的下标
     * @param i    顶点下标
     * @return 下标i这个顶点对应的终点
     */
    public int getEnd(int[] ends, int i) {
        while (ends[i] != 0) {
            i = ends[i];
        }
        return i;
    }
}
