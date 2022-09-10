package dateStructures.recursion;

/**
 * @author Muffin_Head
 * @version 1.8.0_131
 */
public class MazeDemo {
    public static void main(String[] args) {
        int[][] maze = new int[8][7];
        /**
         * 初始化地图信息
         * 0 --> 表示未走过的路
         * 1 --> 墙
         * 2 --> 走得通的路
         * 3 --> 走过但走不通的路
         */
        //第一行 最后一行 第一列 最后一列设置为墙
        for (int i = 0; i < maze[0].length; i++) {
            maze[0][i] = 1;
            maze[maze.length - 1][i] = 1;
        }
        for (int i = 0; i < maze.length; i++) {
            maze[i][0] = 1;
            maze[i][maze[i].length - 1] = 1;
        }

        //设置障碍
        maze[3][1] = maze[3][2] = 1;
        maze[2][2] = 1;
        System.out.println("=============寻路前地图===========");
        Maze.printMap(maze);

        Maze.findWay(maze,1,1);

        System.out.println("=============寻路后地图===========");
        Maze.printMap(maze);
    }
}

/**
 * 迷宫类
 */
class Maze {
    /**
     * 功能:打印地图信息
     *
     * @param map 地图
     */
    public static void printMap(int[][] map) {
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                System.out.printf("%d ", map[i][j]);
            }
            System.out.println();
        }
    }

    /**
     * 功能:寻路
     * 方向规定:下 --> 右 --> 上 --> 左
     *
     * @param map 地图信息
     * @param i   初始位置横坐标
     * @param j   初始位置纵坐标
     * @return boolean
     */
    public static boolean findWay(int[][] map, int i, int j) {
        if (map[map.length - 2][map[0].length - 2] == 2) {
            return true;
        } else {
            if (map[i][j] == 0) {
                map[i][j] = 2;//假定能走通
                if (findWay(map, i + 1, j)) {//下
                    return true;
                } else if (findWay(map, i, j + 1)) {//右
                    return true;
                } else if (findWay(map, i - 1, j)) {//上
                    return true;
                } else if (findWay(map, i, j - 1)) {//左
                    return true;
                } else {
                    map[i][j] = 3;//走过但走不通
                    return false;
                }
            } else {//map[i][j] == 1,2,3
                return false;
            }
        }
    }
}
