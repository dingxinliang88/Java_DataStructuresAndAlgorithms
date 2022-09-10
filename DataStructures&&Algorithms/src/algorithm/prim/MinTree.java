package algorithm.prim;

import java.util.Arrays;

/**
 * @author Muffin_Head
 * @version 1.8.0_131
 */
public class MinTree {
    private static final int MAXNUM = 10000;

    /**
     * 创建图
     *
     * @param mGraph  图对象
     * @param vertexs 顶点数
     * @param data    顶点的数据
     * @param weight  存放顶点间权值
     */
    public void createGraph(MGraph mGraph, int vertexs, char[] data, int[][] weight) {
        for (int i = 0; i < vertexs; i++) {
            mGraph.getData()[i] = data[i];
            for (int j = 0; j < vertexs; j++) {
                mGraph.getWeight()[i][j] = weight[i][j];
            }
        }
    }

    /**
     * 显示图的信息（邻接矩阵）
     *
     * @param mGraph 图对象
     */
    public void showGraph(MGraph mGraph) {
        for (int[] cols : mGraph.getWeight()) {
            System.out.println(Arrays.toString(cols));
        }
    }

    /**
     * prim算法
     *
     * @param mGraph 图对象
     * @param initialVertex 起始顶点
     */
    public void primAlgorithm(MGraph mGraph, int initialVertex) {
        //记录顶点是否被访问
        boolean[] isVisited = new boolean[mGraph.getVertexs()];
        isVisited[initialVertex] = true;//记录当前节点被访问
        int sum = 0;//记录最小权值

        int h1 = -1;
        int h2 = -1;
        int minWeight = MAXNUM;
        for (int k = 1; k < mGraph.getVertexs(); k++) {//共形成（顶点数 - 1）条边
            //确定每次生成的子图和哪个节点距离最近
            for (int i = 0; i < mGraph.getVertexs(); i++) {//访问过的节点
                for (int j = 0; j < mGraph.getVertexs(); j++) {//尚未被访问过的节点
                    if (isVisited[i] && !isVisited[j]
                            && mGraph.getWeight()[i][j] < minWeight) {
                        h1 = i;
                        h2 = j;
                        minWeight = mGraph.getWeight()[i][j];
                    } else if (!isVisited[i]) {
                        break;
                    }
                }
            }

            //找到一条边最小
            System.out.println("连接顶点<" + mGraph.getData()[h1] + "," + mGraph.getData()[h2] + ">，权值："
                    + mGraph.getWeight()[h1][h2]);
            sum += mGraph.getWeight()[h1][h2];
            //设置被访问
            isVisited[h2] = true;
            minWeight = MAXNUM;
        }
        System.out.println("最小路径长度：" + sum);
    }
}
