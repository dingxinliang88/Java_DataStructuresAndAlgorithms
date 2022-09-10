package algorithm.divideandconquer;

/**
 * @author Muffin_Head
 * @version 1.8.0_131
 */
public class HanioTower {
    public static void main(String[] args) {
        move(10,'A','B','C');
    }

    /**
     * 完成汉诺塔的移动
     *
     * @param num 层数
     * @param A   起始塔
     * @param B   辅助塔
     * @param C   终点塔
     */
    public static void move(int num, char A, char B, char C) {
        if (num == 1) {
            System.out.println("第" + num + "个盘：" + A + "->" + C);
        } else {
            //整体看成两个盘
            //1、先将上面的盘借助C移动到B
            move(num - 1, A, C, B);
            //2、再将底下的盘移动到C
            System.out.println("第" + num + "个盘：" + A + "->" + C);
            //3、将B上的盘借助A移动到C
            move(num - 1, B, A, C);
        }
    }
}
