package algorithm.search;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Muffin_Head
 * @version 1.8.0_131
 */
public class InsertValueSearch {
    public static void main(String[] args) {
        int[] arr = new int[100];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = i + 1;
        }
        arr[50] = arr[51] = arr[49] = 49;
//        int resIndex = insertValueSearch(arr, 0, arr.length - 1, 1);
//        System.out.println(resIndex + "-->" + arr[resIndex]);
        List<Integer> list = insertValueSearch2(arr, 0, arr.length - 1, 49);
        for (Integer integer : list) {
            System.out.println(integer + "-->" + arr[integer]);
        }

    }

    /**
     * 插值查找，返回单值
     *
     * @param arr    待查找数组
     * @param left   左索引
     * @param right  右索引
     * @param target 目标值
     * @return 目标值对应数组中元素的下标
     */
    public static int insertValueSearch(int[] arr, int left, int right, int target) {
        //防止mid越界
        if (left > right || target < arr[left] || target > arr[right]) {
            return -1;
        }
        //自适应mid
        int mid = left + (target - arr[left]) * (right - left) / (arr[right] - arr[left]);
        int midVal = arr[mid];
        if (target > midVal) {
            return insertValueSearch(arr, mid + 1, right, target);
        } else if (target < midVal) {
            return insertValueSearch(arr, left, mid - 1, target);
        } else {
            return mid;
        }
    }

    /**
     * 插值查找，返回所有满足条件的值
     * @param arr    待查找数组
     * @param left   左索引
     * @param right  右索引
     * @param target 目标值
     * @return List<Integer>
     */
    public static List<Integer>insertValueSearch2(int[] arr, int left, int right, int target){
        //防止mid越界
        if (left > right || target < arr[left] || target > arr[right]) {
            return new ArrayList<Integer>();
        }
        //自适应mid
        int mid = left + (target - arr[left]) * (right - left) / (arr[right] - arr[left]);
        int midVal = arr[mid];
        if (target > midVal) {
            return insertValueSearch2(arr, mid + 1, right, target);
        } else if (target < midVal) {
            return insertValueSearch2(arr, left, mid - 1, target);
        } else {
            List<Integer> list = new ArrayList<>();
            list.add(mid);
            //向左扫描
            int leftTemp = mid - 1;
            while(leftTemp > -1 && target == arr[leftTemp]){
                list.add(leftTemp--);
            }
            //向右扫描
            int rightTemp = mid + 1;
            while(rightTemp < arr.length && target == arr[rightTemp]){
                list.add(rightTemp++);
            }
            return list;
        }
    }
}
