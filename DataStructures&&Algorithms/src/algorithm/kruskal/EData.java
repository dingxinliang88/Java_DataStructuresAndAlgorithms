package algorithm.kruskal;

/**
 * @author Muffin_Head
 * @version 1.8.0_131
 * 边对象
 */
public class EData {
    private char start;//边的一个顶点
    private char end;//边的另一个顶点
    private int weight;//边的权值

    public EData(char start, char end, int weight) {
        this.start = start;
        this.end = end;
        this.weight = weight;
    }

    public char getStart() {
        return start;
    }

    public void setStart(char start) {
        this.start = start;
    }

    public char getEnd() {
        return end;
    }

    public void setEnd(char end) {
        this.end = end;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "EData [<" + start
                + ", " + end
                + ">="
                + weight + "]";
    }
}
