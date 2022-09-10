package algorithm.prim;

/**
 * @author Muffin_Head
 * @version 1.8.0_131
 */
public class MGraph {
    private int vertexs;//节点数
    private char[] data;//存放节点数据
    private int[][] weight;//存放权值


    public MGraph() {
    }

    public MGraph(int vertexs) {
        this.vertexs = vertexs;
        data = new char[vertexs];
        weight = new int[vertexs][vertexs];
    }

    public int getVertexs() {
        return vertexs;
    }

    public void setVertexs(int vertexs) {
        this.vertexs = vertexs;
    }

    public char[] getData() {
        return data;
    }

    public void setData(char[] data) {
        this.data = data;
    }

    public int[][] getWeight() {
        return weight;
    }

    public void setWeight(int[][] weight) {
        this.weight = weight;
    }
}
