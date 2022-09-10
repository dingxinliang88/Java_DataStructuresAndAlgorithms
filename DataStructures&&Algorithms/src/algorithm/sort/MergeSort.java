package algorithm.sort;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Muffin_Head
 * @version 1.8.0_131
 */
public class MergeSort {
    public static void main(String[] args) {
        int[] testArr = new int[8000000];
        int[] temp = new int[testArr.length];
        for (int i = 0; i < testArr.length; i++) {
            testArr[i] = (int) (Math.random() * 800000);
        }

        //测试归并排序的速度
        long start = System.currentTimeMillis();

        Date date1 = new Date();
        SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateStr1 = simpleDateFormat1.format(date1);
        System.out.println("排序前时间: " + dateStr1);

        mergeSort(testArr, 0, testArr.length - 1,temp);

        Date date2 = new Date();
        SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateStr2 = simpleDateFormat2.format(date2);
        System.out.println("排序后时间:" + dateStr2);

        long end = System.currentTimeMillis();

        System.out.println("排序时间:" + (end - start));
    }

    /**
     * 归并排序 --> 分 + 合
     *
     * @param arr   待排序数组
     * @param left  左索引
     * @param right 右索引
     * @param temp  辅助数组
     */
    public static void mergeSort(int[] arr, int left, int right, int[] temp) {
        if (left < right) {
            int mid = (left + right) / 2;
            //向左递归分解
            mergeSort(arr, left, mid, temp);
            //向右递归分解
            mergeSort(arr, mid + 1, right, temp);

            //每分解一次 --> 合并
            merge(arr,left,mid,right,temp);
        }
    }

    /**
     * 合并
     *
     * @param arr   待排序数组
     * @param left  左索引
     * @param mid   中索引
     * @param right 右索引
     * @param temp  辅助数组
     */
    public static void merge(int[] arr, int left, int mid, int right, int[] temp) {
        int i = left;//左边有序序列的起始下标
        int j = mid + 1;//右边有序序列的下标
        int t = 0;//辅助数组的下标

        //先按照大小顺序处理左右有序序列,填充进temp数组(升序排列)
        while (i <= mid && j <= right) {
            if (arr[i] < arr[j]) {
                temp[t++] = arr[i++];
            } else {
                temp[t++] = arr[j++];
            }
        }
        //处理剩下的数据
        while (i <= mid) {
            temp[t++] = arr[i++];
        }
        while (j <= right) {
            temp[t++] = arr[j++];
        }

        //将temp数组拷贝到arr数组,并不是拷贝所有数据
        t = 0;
        int tempLeft = left;

        while (tempLeft <= right) {
            arr[tempLeft++] = temp[t++];
        }
    }
}
