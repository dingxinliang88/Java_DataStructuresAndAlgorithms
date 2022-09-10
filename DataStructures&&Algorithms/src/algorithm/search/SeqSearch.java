package algorithm.search;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Muffin_Head
 * @version 1.8.0_131
 */
public class SeqSearch {
    public static void main(String[] args) {
        int[] arr = {10, 2, 4, -20, -1, 39, 89, 2};
        List<Integer> list = seqSearch(arr, 1);
            for (Integer item : list) {
                System.out.println(item + "---" + arr[item]);
            }
    }

    /**
     * 顺序查找，返回所有匹配值的下标
     *
     * @param arr    待查找数组
     * @param target 目标值
     * @return List<Integer>
     */
    public static List<Integer> seqSearch(int[] arr, int target) {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < arr.length; i++) {
            if (target == arr[i]) {
                list.add(i);
            }
        }
        return list;
    }


}
