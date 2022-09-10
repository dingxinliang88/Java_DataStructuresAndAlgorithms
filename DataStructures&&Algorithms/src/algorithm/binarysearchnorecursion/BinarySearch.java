package algorithm.binarysearchnorecursion;

/**
 * @author Muffin_Head
 * @version 1.8.0_131
 */
public class BinarySearch {
    public static void main(String[] args) {
        int[] arr = {1,3, 8, 10, 11, 67, 100};
        int index = binarySearch(arr,67);
        System.out.println("index = " + index);
    }


    /**
     * 二分查找 非递归实现
     *
     * @param arr    数组
     * @param target 目标值
     * @return 目标值对应的下标 || -1
     */
    public static int binarySearch(int[] arr, int target) {
        int left = 0;
        int right = arr.length - 1;
        while (left <= right) {
            int mid = (left + right) / 2;
            if(target > arr[mid]){
                left = mid + 1;
            } else if(target < arr[mid]){
                right = mid -1;
            } else {
                return mid;
            }
        }
        return -1;
    }
}
