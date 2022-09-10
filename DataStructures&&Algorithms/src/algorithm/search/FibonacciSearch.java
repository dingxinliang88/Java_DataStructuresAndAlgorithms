package algorithm.search;

import java.util.Arrays;

/**
 * @author Muffin_Head
 * @version 1.8.0_131
 */
public class FibonacciSearch {
    private static int maxSize = 20;

    public static void main(String[] args) {
        int[] arr = {1,8,10,89,1000,1024};
        System.out.println("index = " + fibnacciSearch(arr,89));
    }

    /**
     * 得到斐波那契数列的前maxSize个
     *
     * @return 斐波那契数列
     */
    public static int[] getFib() {
        int[] fib = new int[maxSize];
        fib[0] = fib[1] = 1;
        for (int i = 2; i < fib.length; i++) {
            fib[i] = fib[i - 1] + fib[i - 2];
        }
        return fib;
    }


    /**
     * 斐波那契查找
     *
     * @param arr 待查找数组
     * @param key 目标值
     * @return  int
     */
    public static int fibnacciSearch(int[] arr, int key) {
        int low = 0;
        int high = arr.length - 1;
        int index = 0;//斐波那契分割数值的下标
        int mid = 0;
        int[] fib = getFib();
        //获取斐波那契分割数值的下标
        while (high > fib[index] - 1){
            index ++;
        }
        //数组重新指向
        int[] temp = Arrays.copyOf(arr, fib[index]);
        //使用arr数组的最后的元素填充temp
        for(int i = arr.length;i < temp.length;i++){
            temp[i] = arr[high];
        }

        while(low <= high){
            mid = low + fib[index - 1] - 1;
            if(key < temp[mid]){//继续查找temp的左边
                high = mid - 1;
                index--;
            } else if(key > temp[mid]) {//继续查找temp的右边
                low = mid + 1;
                index -= 2;
            } else {
                if(mid <= high){
                    return mid;
                } else{
                    return high;
                }
            }
        }
        return -1;
    }
}
