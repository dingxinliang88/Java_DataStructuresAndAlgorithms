package algorithm.kmp;

import java.util.Arrays;

/**
 * @author Muffin_Head
 * @version 1.8.0_131
 */
public class KMPAlgorithm {
    public static void main(String[] args) {
        String str1 = "a";
        String str2 = "a";
        int[] next = KMPNext(str2);
        System.out.println(Arrays.toString(next));

        int index = KMPSearch(str1, str2, next);
        System.out.println("index = " + index);
    }


    /**
     * KMP算法
     *
     * @param str1 源字符串
     * @param str2 子串
     * @param next 子串的部分匹配值
     * @return 匹配的下标 || -1
     */
    public static int KMPSearch(String str1, String str2, int[] next) {
        for (int i = 0, j = 0; i < str1.length(); i++) {
            while (j > 0 && str1.charAt(i) != str2.charAt(j)) {
                j = next[j - 1];
            }
            if (str1.charAt(i) == str2.charAt(j)) {
                j++;
            }
            if (j == str2.length()) {
                return i - j + 1;
            }
        }
        return -1;
    }

    /**
     * 获得字符串的部分匹配值
     *
     * @param dest 字符串
     * @return int[]
     */
    public static int[] KMPNext(String dest) {
        int[] next = new int[dest.length()];
        next[0] = 0;//如果字符串长度为1，部分匹配值就是0
        for (int i = 1, j = 0; i < dest.length(); i++) {
            //当dest.charAt(i) != dest.charAt(j) ，我们需要从next[j-1]获取新的j
            //直到我们发现 有  dest.charAt(i) == dest.charAt(j)成立才退出
            while (j > 0 && dest.charAt(i) != dest.charAt(j)) {
                j = next[j - 1];
            }
            if (dest.charAt(i) == dest.charAt(j)) {
                j++;
            }
            next[i] = j;
        }
        return next;
    }
}
