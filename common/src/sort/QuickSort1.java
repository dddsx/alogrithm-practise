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

        int pivot = partition(arr, start, end);
        sort(arr, start, pivot - 1);
        sort(arr, pivot + 1, end);
    }
    
    private static int partition(int[] arr, int low, int hight) {
        int left = low; // 左指针
        int right = hight; // 右指针

        int key = arr[low];

        // 口诀：从右向左找小于，从左向右找大于
        while (left < right) {
            // arr[right]和key比较可以用">"或">="
            while (left < right && arr[right] > key) {
                right--;
            }
            // 这里必须用"<="，因为left指针必须要跳过选择的key
            while (left < right && arr[left] <= key) {
                left++;
            }

            if (left < right) {
                swap(arr, left, right);
            }
        }
        // 当left和right指针重合时，刚好指向基准位置，且此元素肯定小于等于low
        // 将low位和left位做交换
        swap(arr, low, left);
        
        return left;
    }
    
    private static void swap(int[] arr, int l, int r) {
        int temp = arr[l];
        arr[l] = arr[r];
        arr[r] = temp;
    }
}
