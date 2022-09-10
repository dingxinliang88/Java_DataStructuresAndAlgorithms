package algorithm.sort;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Muffin_Head
 * @version 1.8.0_131
 */
public class SelectSort {
    public static void main(String[] args) {
        int[] testArr = new int[80000];
        for (int i = 0; i < testArr.length; i++) {
            testArr[i] = (int) (Math.random() * 800000);
        }
        //测试选择排序的速度,选择排序时间复杂度O(n²)
        long start = System.currentTimeMillis();

        Date date1 = new Date();
        SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateStr1 = simpleDateFormat1.format(date1);
        System.out.println("排序前时间: " + dateStr1);

        selectSort(testArr);

        Date date2 = new Date();
        SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateStr2 = simpleDateFormat2.format(date2);
        System.out.println("排序后时间:" + dateStr2);

        long end = System.currentTimeMillis();

        System.out.println("排序时间:" + (end - start));
    }


    /**
     * 选择排序
     *
     * @param arr 待排序数组
     */
    public static void selectSort(int[] arr) {
        int min, minIndex;

        for (int i = 0; i < arr.length - 1; i++) {
            //假定当前趟的第一个元素是最小值
            min = arr[i];
            minIndex = i;
            for (int j = i; j < arr.length; j++) {
                if (min > arr[j]) {
                    //假定的最小值不是最小值,更新最小值和最小值下标
                    min = arr[j];
                    minIndex = j;
                }
            }

            //当前趟进行了更新 --> 交换
            if (minIndex != i) {
                arr[minIndex] = arr[i];
                arr[i] = min;
            }
        }
    }
}
