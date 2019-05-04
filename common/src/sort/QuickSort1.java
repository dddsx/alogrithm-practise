package sort;

/**
 * 参考：http://ju.outofmemory.cn/entry/372908
 */
public class QuickSort1 {
    
    /**
     * 快速排序（左右指针法）
     * @param arr   待排序数组
     * @param start 左边界
     * @param end   右边界
     */
    public static void sort(int[] arr, int start, int end) {
        if (start >= end) {
            return;
        }
        int mid = partition(arr, start, end);
        sort(arr, start, mid - 1);
        sort(arr, mid + 1, end);
    }
    
    private static int partition(int[] arr, int left, int right) {
        int pivot = right;
        
        while (left < right) {
            while (left < right && arr[left] <= arr[pivot]) {
                left++;
            }
            
            while (left < right && arr[right] >= arr[pivot]) {
                right--;
            }
            
            if (left != right) {
                swap(arr, left, right);
            }
        }
        
        swap(arr, left, pivot);
        
        return left;
    }
    
    private static void swap(int[] arr, int l, int r) {
        int temp = arr[l];
        arr[l] = arr[r];
        arr[r] = temp;
    }
}
