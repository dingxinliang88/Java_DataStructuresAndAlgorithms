package algorithm.sort;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Muffin_Head
 * @version 1.8.0_131
 */
public class ShellSort {
    public static void main(String[] args) {

        int[] testArr = new int[80000];
        for (int i = 0; i < testArr.length; i++) {
            testArr[i] = (int) (Math.random() * 800000);
        }
        //测试希尔排序的速度
        long start = System.currentTimeMillis();

        Date date1 = new Date();
        SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateStr1 = simpleDateFormat1.format(date1);
        System.out.println("排序前时间: " + dateStr1);

        shellSort2(testArr);

        Date date2 = new Date();
        SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateStr2 = simpleDateFormat2.format(date2);
        System.out.println("排序后时间:" + dateStr2);

        long end = System.currentTimeMillis();

        System.out.println("排序时间:" + (end - start));

    }

    /**
     * 希尔排序(交换法)
     *
     * @param arr 待排序数组
     */
    public static void shellSort(int[] arr) {
        int temp = 0;
        //分组,每次gap组,步长gap
        for (int gap = arr.length / 2; gap > 0; gap /= 2) {
            for (int i = gap; i < arr.length; i++) {
                for (int j = i - gap; j >= 0; j -= gap) {
                    //若当前元素大于下标加上步长后的元素 --> 交换 [升序排列]
                    if (arr[j] > arr[j + gap]) {
                        temp = arr[j];
                        arr[j] = arr[j + gap];
                        arr[j + gap] = temp;
                    }
                }
            }
        }
    }

    /**
     * 希尔排序(移位法)
     *
     * @param arr 待排序数组
     */
    public static void shellSort2(int[] arr) {
        //分组,每次gap组,步长gap
        for (int gap = arr.length / 2; gap > 0; gap /= 2) {
            //从第gap个元素开始,对其所在的组进行直接插入排序
            for (int i = gap; i < arr.length; i++) {
                int insertIndex = i;
                int insertVal = arr[i];
                if (arr[insertIndex] < arr[insertIndex - gap]) {
                    while (insertIndex - gap >= 0 && insertVal < arr[insertIndex]) {
                        arr[insertIndex] = arr[insertIndex - gap];
                        insertIndex -= gap;
                    }
                    arr[insertIndex] = insertVal;
                }
            }
        }
    }
}
