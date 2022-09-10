package algorithm.floyd;

import java.util.Arrays;

/**
 * @author Muffin_Head
 * @version 1.8.0_131
 */
public class Graph {
    private static final int N = 65535;
    private char[] vertexs;//存放顶点的集合
    private int[][] distence;//距离
    private int[][] preVertex;//前驱节点


    public Graph(int numOfVertex, char[] vertexs, int[][] distence) {
        this.vertexs = vertexs;
        this.distence = distence;
        this.preVertex = new int[numOfVertex][numOfVertex];
        //初始化preVertex数组
        for (int i = 0; i < numOfVertex; i++) {
            Arrays.fill(preVertex[i], i);
        }
    }

    /**
     * 显示最后结果
     */
    public void show() {
        for (int k = 0; k < vertexs.length; k++) {
            for (int i = 0; i < vertexs.length; i++) {
                System.out.print(vertexs[preVertex[k][i]] + "\t\t\t\t");
            }

            System.out.println();
            for (int i = 0; i < distence.length; i++) {
                if (distence[k][i] != N) {
                    System.out.print("<" + vertexs[k] + "->" + vertexs[i] + ">-->" + distence[k][i] + "\t\t");
                } else {
                    System.out.print("<" + vertexs[k] + "->" + vertexs[i] + ">-->N\t\t");
                }
            }
            System.out.println();
        }
    }

    public void floydAlgorithm() {
        int length = 0;
        //遍历中间顶点
        for (int k = 0; k < distence.length; k++) {
            //遍历起始顶点
            for (int i = 0; i < distence.length; i++) {
                //遍历终点
                for (int j = 0; j < distence.length; j++) {
                    length = distence[i][k] + distence[k][j];
                    if (length < distence[i][j]) {
                        distence[i][j] = length;
                        preVertex[i][j] = preVertex[k][j];
                    }
                }
            }
        }
    }
}
