package algorithm.dijkstra;

/**
 * @author Muffin_Head
 * @version 1.8.0_131
 */
public class DijkstraDemo {
    private static final int N = 65535;

    public static void main(String[] args) {
        char[] vertexs = { 'A', 'B', 'C', 'D', 'E', 'F', 'G' };
        int[][] weight = new int[vertexs.length][vertexs.length];
        weight[0]=new int[]{N,5,7,N,N,N,2};
        weight[1]=new int[]{5,N,N,9,N,N,3};
        weight[2]=new int[]{7,N,N,N,8,N,N};
        weight[3]=new int[]{N,9,N,N,N,4,N};
        weight[4]=new int[]{N,N,8,N,N,5,4};
        weight[5]=new int[]{N,N,N,4,5,N,6};
        weight[6]=new int[]{2,3,N,N,4,6,N};

        Graph graph = new Graph(vertexs, weight);
        graph.showGraph();
        graph.dijkstraAlgorithm(6);
        graph.show();
    }
}
