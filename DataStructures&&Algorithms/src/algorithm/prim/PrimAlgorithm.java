package algorithm.prim;

/**
 * @author Muffin_Head
 * @version 1.8.0_131
 */
public class PrimAlgorithm {
    public static void main(String[] args) {

        char[] data = new char[]{'A', 'B', 'C', 'D', 'E', 'F', 'G'};
        int vertexs = data.length;
        //邻接矩阵的关系使用二维数组表示,10000这个大数，表示两个点不联通
        int[][] weight = new int[][]{
                {10000, 5, 7, 10000, 10000, 10000, 2},
                {5, 10000, 10000, 9, 10000, 10000, 3},
                {7, 10000, 10000, 10000, 8, 10000, 10000},
                {10000, 9, 10000, 10000, 10000, 4, 10000},
                {10000, 10000, 8, 10000, 10000, 5, 4},
                {10000, 10000, 10000, 4, 5, 10000, 6},
                {2, 3, 10000, 10000, 4, 6, 10000},};
        MGraph mGraph = new MGraph(vertexs);
        MinTree minTree = new MinTree();
        minTree.createGraph(mGraph, vertexs, data, weight);
        minTree.showGraph(mGraph);
        minTree.primAlgorithm(mGraph, 1);
    }
}
