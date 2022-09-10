package dateStructures.recursion;

/**
 * @author Muffin_Head
 * @version 1.8.0_131
 */
public class EightQueen {
    static int max = 8;//皇后数量
    static int[] array = new int[max];//下标代表行,元素代表列
    static int count = 0;//解法数
    static int judgeCount = 0;//判断次数

    public static void main(String[] args) {
        check(0);
        System.out.println("共检测次数:" + judgeCount);
        System.out.println("解法数:" + count);
    }

    /**
     * 功能:放置皇后
     *
     * @param n 当前皇后数
     */
    public static void check(int n) {
        if (n == max) {
            printArray();
            return;
        }
        //依次放置皇后
        for (int i = 0; i < max; i++) {
            array[n] = i;//按行依次向后放置
            if (judge(n)) {
                check(n + 1);//不冲突继续检测后一个
            }
            //如果冲突,将当前皇后后移一位
        }
    }


    /**
     * 功能:判断皇后位置是否合理
     *
     * @param n 当前皇后数
     * @return boolean
     */
    public static boolean judge(int n) {
        judgeCount++;
        for (int i = 0; i < n; i++) {//行自动检测
            //检测列和对角线
            if (array[i] == array[n] || Math.abs(n - i) == Math.abs(array[n] - array[i])) {
                return false;
            }
        }
        return true;
    }

    /**
     * 功能:打印结果
     */
    public static void printArray() {
        count++;
        for (int item : array) {
            System.out.printf("%d ", item);
        }
        System.out.println("\n=======================");
    }
}

