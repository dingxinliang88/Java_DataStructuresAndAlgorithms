package algorithm.dynamic;

import java.util.Arrays;

/**
 * @author Muffin_Head
 * @version 1.8.0_131
 * 背包问题
 */
public class KnapsackProblem {
    public static void main(String[] args) {
        int[] w = {1, 4, 3};//物体的重量
        int[] val = {1500, 3000, 2000};//物体的价格
        int m = 4;//背包的容量
        int n = val.length;
        ;//物品的个数

        //v[i][j]表示在前i个物品中能装入容量为j的背包的最大价值
        int[][] v = new int[n + 1][m + 1];
        //记录放入物品的情况
        int[][] path = new int[n + 1][m + 1];

        /*
        v[i][0] = v[0][j] = 0;默认为0
        保证v[i][j]表示在前i个物品中能装入容量为j的背包的最大价值
         */

        //动态规划处理，从第一行第一列开始
        for (int i = 1; i < v.length; i++) {
            for (int j = 1; j < v[0].length; j++) {
                if (w[i - 1] > j) {//新增物品的重量大于背包容量
                    v[i][j] = v[i - 1][j];
                } else {
                    //因为我们的i 从1开始的，需要调整公式
                    //原公式：v[i][j]=Math.max(v[i][j], val[i]+v[i-1][j-w[i]]);
                    //v[i][j] = Math.max(v[i - 1][j], val[i - 1] + v[i - 1][j - w[i - 1]]);
                    if (v[i - 1][j] < val[i - 1] + v[i - 1][j - w[i - 1]]) {
                        v[i][j] = val[i - 1] + v[i - 1][j - w[i - 1]];
                        path[i][j] = 1;
                    } else {
                        v[i][j] = v[i-1][j];
                    }
                }
            }
        }
        //输出v[][]的情况
        for (int[] vs : v) {
            System.out.println(Arrays.toString(vs));
        }
        //输出path[][]的情况（倒序输出）
        int i = v.length - 1;
        int j = v[0].length -1 ;
        while(i > 0 && j > 0){
            if(path[i][j] == 1){
                System.out.printf("第%d个商品放入背包\n",i);
                j -= w[i-1];//减去对应重量
            }
            i--;
        }
    }
}
