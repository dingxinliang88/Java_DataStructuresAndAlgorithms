package algorithm.search;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Muffin_Head
 * @version 1.8.0_131
 */
public class BinarySearch {
    public static void main(String[] args) {
        int[] arr = {1, 20, 90, 100, 233, 233, 233, 233, 456};
        int res = binarySearch(arr, 0, arr.length - 1, 233);
        System.out.println(res + "-->" + arr[res]);
        List<Integer> list = binarySearch2(arr, 0, arr.length - 1, 233);
        System.out.println("===============================================");
        for (Integer integer : list) {
            System.out.println(integer + "-->" + arr[integer]);
        }
    }

    /**
     * 二分查找（返回单值）
     *
     * @param arr    待查找数组
     * @param left   左索引
     * @param right  右索引
     * @param target 目标值
     * @return 目标值在数组中对应的下标，未找到返回-1
     */
    public static int binarySearch(int[] arr, int left, int right, int target) {
        if (left > right) {
            return -1;
        }
        int mid = (left + right) / 2;
        int midVal = arr[mid];
        if (target > midVal) {
            return binarySearch(arr, mid + 1, right, target);
        } else if (target < midVal) {
            return binarySearch(arr, left, mid - 1, target);
        } else {
            return mid;
        }
    }

    /**
     * 二分查找（返回所有满足条件的值）
     *
     * @param arr    待查找数组
     * @param left   左索引
     * @param right  右索引
     * @param target 目标值
     * @return 满足条件值的集合
     */
    public static List<Integer> binarySearch2(int[] arr, int left, int right, int target) {
        if (left > right) {
            return new ArrayList<Integer>();
        }

        int mid = (left + right) / 2;
        int midVal = arr[mid];

        if (target > midVal) {
            return binarySearch2(arr, mid + 1, right, target);
        } else if (target < midVal) {
            return binarySearch2(arr, left, mid - 1, target);
        } else {
            List<Integer> list = new ArrayList<>();
            list.add(mid);
            //向左扫描
            int leftTemp = mid - 1;
            while (leftTemp > 0 && arr[leftTemp] == target) {
                list.add(leftTemp--);
            }
            //向右扫描
            int rightTemp = mid + 1;
            while (rightTemp < arr.length && arr[rightTemp] == target) {
                list.add(rightTemp++);
            }
            return list;
        }
    }
}
