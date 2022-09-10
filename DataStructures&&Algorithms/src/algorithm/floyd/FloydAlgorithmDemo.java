package algorithm.floyd;

/**
 * @author Muffin_Head
 * @version 1.8.0_131
 */
public class FloydAlgorithmDemo {
    private static final int N = 65535;

    public static void main(String[] args) {
        char[] vertex = {'A', 'B', 'C', 'D', 'E', 'F', 'G'};
        //创建邻接矩阵
        int[][] distence = new int[vertex.length][vertex.length];
        distence[0] = new int[]{0, 5, 7, N, N, N, 2};
        distence[1] = new int[]{5, 0, N, 9, N, N, 3};
        distence[2] = new int[]{7, N, 0, N, 8, N, N};
        distence[3] = new int[]{N, 9, N, 0, N, 4, N};
        distence[4] = new int[]{N, N, 8, N, 0, 5, 4};
        distence[5] = new int[]{N, N, N, 4, 5, 0, 6};
        distence[6] = new int[]{2, 3, N, N, 4, 6, 0};

        Graph graph = new Graph(vertex.length, vertex, distence);
        graph.floydAlgorithm();
        graph.show();
    }
}
