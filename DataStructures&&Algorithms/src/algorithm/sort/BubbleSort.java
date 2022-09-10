package algorithm.sort;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Muffin_Head
 * @version 1.8.0_131
 */
public class BubbleSort {
    public static void main(String[] args) {

        int[] testArr = new int[80000];
        for (int i = 0; i < testArr.length; i++) {
            testArr[i] = (int) (Math.random() * 80000);
        }
        //测试冒泡排序的速度,冒泡排序时间复杂度O(n²)
        long start = System.currentTimeMillis();

        Date date1 = new Date();
        SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateStr1 = simpleDateFormat1.format(date1);
        System.out.println("排序前时间: " + dateStr1);

        bubbleSort(testArr);

        Date date2 = new Date();
        SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateStr2 = simpleDateFormat2.format(date2);
        System.out.println("排序后时间:" + dateStr2);

        long end = System.currentTimeMillis();

        System.out.println("排序时间:" + (end - start));
    }

    /**
     * 冒泡排序
     *
     * @param arr 待排序数组
     */
    public static void bubbleSort(int[] arr) {
        int temp;//临时变量
        boolean flag = false;//用于优化
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - 1 - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    flag = true;
                    temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
//            System.out.printf("第%d趟排序后的结果:", i + 1);
//            System.out.println(Arrays.toString(arr));
            if (!flag) {//未进行排序
                break;
            }
            flag = false;//重置flage,进行下一轮判断
        }
//        System.out.print("最后排序的结果:");
//        System.out.println(Arrays.toString(arr));
    }
}
