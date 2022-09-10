package algorithm.sort;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Muffin_Head
 * @version 1.8.0_131
 */
public class InsertSort {
    public static void main(String[] args) {
        int[] testArr = new int[100000];
        for (int i = 0; i < testArr.length; i++) {
            testArr[i] = (int) (Math.random() * 800000);
        }
        //测试插入排序的速度
        long start = System.currentTimeMillis();

        Date date1 = new Date();
        SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateStr1 = simpleDateFormat1.format(date1);
        System.out.println("排序前时间: " + dateStr1);

        insertSort(testArr);

        Date date2 = new Date();
        SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateStr2 = simpleDateFormat2.format(date2);
        System.out.println("排序后时间:" + dateStr2);

        long end = System.currentTimeMillis();

        System.out.println("排序时间:" + (end - start));

    }

    /**
     * 插入排序
     *
     * @param arr 待排序数组
     */
    public static void insertSort(int[] arr) {
        int insertIndex, insertVal;
        for (int i = 1; i < arr.length; i++) {
            insertVal = arr[i];
            insertIndex = i - 1;
            //不越界 && 升序排列
            while (insertIndex >= 0 && insertVal < arr[insertIndex]) {
                arr[insertIndex + 1] = arr[insertIndex];//arr[insertIndex]后移
                insertIndex--;
            }

            //当前轮进行过后移,需要赋值
//            if(insertIndex + 1 != i) {
            arr[insertIndex + 1] = insertVal;
//            }
        }
    }
}
