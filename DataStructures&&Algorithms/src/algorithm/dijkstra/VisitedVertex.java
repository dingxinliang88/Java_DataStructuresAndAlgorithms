package algorithm.dijkstra;

import java.util.Arrays;

/**
 * @author Muffin_Head
 * @version 1.8.0_131
 * 已访问顶点集合
 */
public class VisitedVertex {
    private static final int N = 65535;
    private boolean[] isVisited;//记录顶点是否被访问过
    private int[] preVisited;//每一个被访问节点的前一个顶点
    private int[] distence;//记录出发顶点到其他顶点的距离
    private int initialIndex;//起始顶点

    /**
     * 构造器
     * @param numOfVertex   顶点个数
     * @param initialIndex  出发顶点
     */
    public VisitedVertex(int numOfVertex,int initialIndex) {
        this.isVisited = new boolean[numOfVertex];
        this.preVisited = new int[numOfVertex];
        this.distence = new int[numOfVertex];
        this.initialIndex = initialIndex;

        //初始化distence数组
        Arrays.fill(distence,N);
        this.distence[initialIndex] = 0;
        this.isVisited[initialIndex] = true;//记录出发顶点已被访问
    }

    public int getInitialIndex() {
        return initialIndex;
    }

    public int[] getDistence() {
        return distence;
    }

    /**
     * 返回index顶点是否被访问过
     * @param index 顶点下标
     * @return  boolean
     */
    public boolean isVisitedByIndex(int index){
        return isVisited[index];
    }

    /**
     * 更新出发顶点到index顶点的距离
     * @param index 顶点下标
     * @param length    距离
     */
    public void updateDistence(int length ,int index){
        distence[index] = length;
    }

    /**
     * 更新cur顶点的前驱顶点为pre
     * @param cur   需要更新的节点
     * @param pre   需要更新节点的前驱节点
     */
    public void updatePre(int cur,int pre){
        preVisited[cur] = pre;
    }

    /**
     * 获取出发顶点到index顶点的距离
     * @param index 顶点下标
     * @return  int
     */
    public int getDistence(int index){
        return distence[index];
    }

    /**
     * 继续选择并返回新的顶点下标
     * @return  int
     */
    public int updateInitialVertex(){
        int min = N,index = 0;
        for (int i = 0; i < isVisited.length; i++) {
            if(!isVisited[i] && distence[i] < min){
                min = distence[i];
                index = i;
            }
        }
        //更新index顶点已被访问
        isVisited[index] = true;
        return index;
    }
}
