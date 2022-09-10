package algorithm.sort;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Muffin_Head
 * @version 1.8.0_131
 */
public class RadixSort {
    public static void main(String[] args) {
        int[] testArr = new int[8000000];
        for (int i = 0; i < testArr.length; i++) {
            testArr[i] = (int) (Math.random() * 800000);
        }
        //测试基数排序的速度
        long start = System.currentTimeMillis();

        Date date1 = new Date();
        SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateStr1 = simpleDateFormat1.format(date1);
        System.out.println("排序前时间: " + dateStr1);

        radixSort(testArr);

        Date date2 = new Date();
        SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateStr2 = simpleDateFormat2.format(date2);
        System.out.println("排序后时间:" + dateStr2);

        long end = System.currentTimeMillis();

        System.out.println("排序时间:" + (end - start));
    }

    /**
     * 基数排序
     *
     * @param arr 待排序数组
     */
    public static void radixSort(int[] arr) {
        //定义10个桶,每个桶就是一个一维数组
        int[][] bucket = new int[10][arr.length];//空间换时间
        //定义记录每个桶内数据的个数的数组
        int[] bucketElemCount = new int[10];

        //得到数组中最大值的位数
        int max = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > max) {
                max = arr[i];
            }
        }
        int maxLength = (max + "").length();

        for (int i = 0, n = 1; i < maxLength; i++, n *= 10) {
            for (int item : arr) {
                //得到数组中每个元素对应位的值
                int dightOfElem = item / n % 10;
                //存入对应的桶内,桶内数据数++
                bucket[dightOfElem][bucketElemCount[dightOfElem]++] = item;
            }

            int index = 0;
            //遍历每一个桶取出数据,存入arr
            for (int k = 0; k < bucketElemCount.length; k++) {
                //对应桶内数据不为空
                if (bucketElemCount[k] != 0) {
                    for (int l = 0; l < bucketElemCount[k]; l++) {
                        arr[index++] = bucket[k][l];
                    }
                }
                //对应记录桶内数据数的变量置零
                bucketElemCount[k] = 0;
            }
        }
    }
}
