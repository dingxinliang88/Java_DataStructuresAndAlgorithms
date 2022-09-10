package algorithm.kruskal;

import java.util.Arrays;

/**
 * @author Muffin_Head
 * @version 1.8.0_131
 */
public class KruskalAlgorithmDemo {
    private static final int INF = Integer.MAX_VALUE;

    public static void main(String[] args) {

        char[] vertexs = {'A', 'B', 'C', 'D', 'E', 'F', 'G'};
        //克鲁斯卡尔算法的邻接矩阵
        int[][] weight = {
                /*A*//*B*//*C*//*D*//*E*//*F*//*G*/
                /*A*/ {0, 12, INF, INF, INF, 16, 14},
                /*B*/ {12, 0, 10, INF, INF, 7, INF},
                /*C*/ {INF, 10, 0, 3, 5, 6, INF},
                /*D*/ {INF, INF, 3, 0, 4, INF, INF},
                /*E*/ {INF, INF, 5, 4, 0, 2, 8},
                /*F*/ {16, 7, 6, INF, 2, 0, 9},
                /*G*/ {14, INF, INF, INF, 8, 9, 0}};

        KruskalAlgorithm kruskalAlgorithm = new KruskalAlgorithm(vertexs, weight);
//        kruskalAlgorithm.print();
//        EData[] edges = kruskalAlgorithm.getEdges();
//        System.out.println(Arrays.toString(edges));
//        kruskalAlgorithm.sortEdges(edges);
//        System.out.println(Arrays.toString(edges));

        kruskalAlgorithm.kruskalAlgorithm();

    }
}
