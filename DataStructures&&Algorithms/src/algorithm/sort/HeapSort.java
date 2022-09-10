package algorithm.sort;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

/**
 * @author Muffin_Head
 * @version 1.8.0_131
 */
public class HeapSort {
    public static void main(String[] args) {
//        int[] arr = {4, 6, 8, 5, 9};
//        heapSort(arr);
//        System.out.println(Arrays.toString(arr));
        int[] testArr = new int[80000000];
        for (int i = 0; i < testArr.length; i++) {
            testArr[i] = (int) (Math.random() * 800000);
        }
        //测试堆排序的速度
        long start = System.currentTimeMillis();

        Date date1 = new Date();
        SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateStr1 = simpleDateFormat1.format(date1);
        System.out.println("排序前时间: " + dateStr1);

        heapSort(testArr);

        Date date2 = new Date();
        SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateStr2 = simpleDateFormat2.format(date2);
        System.out.println("排序后时间:" + dateStr2);

        long end = System.currentTimeMillis();

        System.out.println("排序时间:" + (end - start));
    }

    /**
     * 堆排序
     *
     * @param arr 待排序数组
     */
    public static void heapSort(int[] arr) {
        int temp;
        //将无序序列构建成一个堆
        for (int i = arr.length / 2 - 1; i >= 0; i--) {
            adjustArr(arr, i, arr.length);
        }
        //将堆顶元素与末尾元素交换，将最大元素"沉"到数组末端;
        //重新调整结构，使其满足堆定义，然后继续交换堆顶元素与当前末尾元素，
        //反复执行调整+交换步骤，直到整个序列有序
        for (int j = arr.length - 1; j > 0; j--) {
            temp = arr[j];
            arr[j] = arr[0];
            arr[0] = temp;
            adjustArr(arr, 0, j);
        }
    }

    /**
     * 调整二叉树成大顶堆
     *
     * @param arr    待调整数组
     * @param index  起始非叶子节点下标
     * @param length 需要调整元素的个数
     */
    public static void adjustArr(int[] arr, int index, int length) {
        int temp = arr[index];

        //调整当前非叶子节点的左子节点
        for (int k = 2 * index + 1; k < length; k = 2 * k + 1) {
            //右子节点的值大于左子节点
            if (k + 1 < length && arr[k + 1] > arr[k]) {
                k++;//指向右子节点
            }
            //子节点大于父节点
            if (arr[k] > arr[index]) {
                arr[index] = arr[k];
                index = k;//更改调整的下标
            }
        }
        //退出循环时，已经将以index为父节点的树的最大值放在了顶部
        //放置temp调整后的值
        arr[index] = temp;
    }
}
