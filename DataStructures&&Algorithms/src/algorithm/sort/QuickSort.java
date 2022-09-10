package algorithm.sort;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Muffin_Head
 * @version 1.8.0_131
 */
public class QuickSort {
    public static void main(String[] args) {
        int[] testArr = new int[8000000];
        for (int i = 0; i < testArr.length; i++) {
            testArr[i] = (int) (Math.random() * 800000);
        }
        //测试快速排序的速度
        long start = System.currentTimeMillis();

        Date date1 = new Date();
        SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateStr1 = simpleDateFormat1.format(date1);
        System.out.println("排序前时间: " + dateStr1);

        quickSort(testArr, 0, testArr.length - 1);

        Date date2 = new Date();
        SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateStr2 = simpleDateFormat2.format(date2);
        System.out.println("排序后时间:" + dateStr2);

        long end = System.currentTimeMillis();

        System.out.println("排序时间:" + (end - start));
    }

    /**
     * 快速排序
     *
     * @param arr   待排序数组
     * @param left  左索引
     * @param right 右索引
     */
    public static void quickSort(int[] arr, int left, int right) {
        if (left >= right) {
            return;
        }

        int fiducialIndex = (left + right) / 2;//基准下标
        int fiducialVal = arr[fiducialIndex];//基准值
        int l = left, r = right;//索引
        int temp = 0;
        while (l < r) {
            //右边的值比fiducialVal大
            while (fiducialVal < arr[r]) {
                r--;
            }
            //左边的值比fiducialVal小
            while (fiducialVal > arr[l]) {
                l++;
            }

            if (l >= r) {
                break;
            }

            temp = arr[l];
            arr[l] = arr[r];
            arr[r] = temp;

            //如果交换完,arr[l] == fiducialVal,r--
            if (arr[l] == fiducialVal) {
                r--;
            }
            //如果交换完,arr[r] == fiducialVal,l++
            if (arr[r] == fiducialVal) {
                l++;
            }
        }
        //防止栈溢出
        if (r == l) {
            r--;
            l++;
        }
        //向左递归
        //if(left < r){
        quickSort(arr, left, r);
        // }

        //向右递归
        //if(right > l){
        quickSort(arr, l, right);
        // }
    }


}
