package algorithm.horsestepboard;

import java.awt.*;
import java.util.ArrayList;
import java.util.Comparator;

/**
 * @author Muffin_Head
 * @version 1.8.0_131
 */
public class HorseChessboard {
    private static int X;//棋盘的行数
    private static int Y;//棋盘的列数
    private static boolean isVisited[];//记录棋盘的位置是否被访问过
    private static boolean isFinished;//完成标志

    public static void main(String[] args) {
        System.out.println("骑士开始周游...");
        X = 8;
        Y = 8;
        int row = 1;//马起始位置的行
        int column = 1;//马起始位置的列

        int[][] chessBoard = new int[X][Y];
        isVisited = new boolean[X*Y];
        long start = System.currentTimeMillis();
        travelsalChessBoard(chessBoard, row - 1, column - 1, 1);
        long end = System.currentTimeMillis();
        System.out.println("共耗时: " + (end - start) + " 毫秒");


        for (int[] rows : chessBoard) {
            for (int col : rows) {
                System.out.print(col + "\t");
            }
            System.out.println();
        }
    }

    /**
     * 骑士周游算法
     *
     * @param chessBoard 棋盘
     * @param row        当前马位置的行
     * @param column     当前马位置的列
     * @param step       第几步（起始为1）
     */
    public static void travelsalChessBoard(int[][] chessBoard, int row, int column, int step) {
        chessBoard[row][column] = step;
        isVisited[row * X + column] = true;
        //获取下一步可以走的位置的集合
        ArrayList<Point> pointArrayList = next(new Point(row, column));

        //对pointArrayList排序
        sort(pointArrayList);

        while (!pointArrayList.isEmpty()) {
            //取出下一个可以走的位置
            Point point = pointArrayList.remove(0);
            if (!isVisited[point.y * X + point.x]) {//尚未被访问
                travelsalChessBoard(chessBoard, point.y, point.x, step + 1);
            }
        }

        if (step < X * Y && !isFinished) {
            chessBoard[row][column] = 0;
            isVisited[row * X + column] = false;
        } else {
            isFinished = true;
        }
    }

    /**
     * 根据当前位置，得到还能走的位置，放入ArrayList
     *
     * @param curPoint 当前位置
     * @return 能走的位置集合
     */
    public static ArrayList<Point> next(Point curPoint) {
        ArrayList<Point> pointArrayList = new ArrayList<>();
        Point point = new Point();
        //根据马走日的规则判断
        if ((point.x = curPoint.x - 2) >= 0 && (point.y = curPoint.y - 1) >= 0) {
            pointArrayList.add(new Point(point));
        }
        if ((point.x = curPoint.x - 1) >= 0 && (point.y = curPoint.y - 2) >= 0) {
            pointArrayList.add(new Point(point));
        }
        if ((point.x = curPoint.x + 1) < X && (point.y = curPoint.y - 2) >= 0) {
            pointArrayList.add(new Point(point));
        }
        if ((point.x = curPoint.x + 2) < X && (point.y = curPoint.y - 1) >= 0) {
            pointArrayList.add(new Point(point));
        }
        if ((point.x = curPoint.x + 2) < X && (point.y = curPoint.y + 1) < Y) {
            pointArrayList.add(new Point(point));
        }
        if ((point.x = curPoint.x + 1) < X && (point.y = curPoint.y + 2) < Y) {
            pointArrayList.add(new Point(point));
        }
        if ((point.x = curPoint.x - 1) >= 0 && (point.y = curPoint.y + 2) < Y) {
            pointArrayList.add(new Point(point));
        }
        if ((point.x = curPoint.x - 2) >= 0 && (point.y = curPoint.y + 1) < Y) {
            pointArrayList.add(new Point(point));
        }
        return pointArrayList;
    }


    /**
     * 对下一步走的位置的多少进行非递减排序，减少回溯的次数
     * @param pointArrayList    能走的位置集合
     */
    public static void sort(ArrayList<Point> pointArrayList){
        pointArrayList.sort(new Comparator<Point>() {
            @Override
            public int compare(Point o1, Point o2) {
                return next(o1).size() - next(o2).size();
            }
        });
    }
}
