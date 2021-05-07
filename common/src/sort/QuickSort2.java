package sort;


public class QuickSort2 implements Sortable {

    @Override
    public void sort(int[] arr) {
        sort(arr, 0, arr.length - 1);
    }

    /**
     * 快速排序（挖坑法递归）
     * @param arr  待排序数组
     * @param low  左边界
     * @param high 右边界
     */
    public void sort(int[] arr, int low, int high) {
        if (arr == null || arr.length <= 0) {
            return;
        }
        if (low >= high) {
            return;
        }
        
        int left = low;
        int right = high;
        int temp = arr[left]; //挖坑1：保存基准的值
        
        while (left < right) {
            while (left < right && arr[right] >= temp) {
                right--;
            }
            arr[left] = arr[right]; //坑2：从后向前找到比基准小的元素，插入到基准位置坑1中
            while (left < right && arr[left] <= temp) {
                left++;
            }
            arr[right] = arr[left]; //坑3：从前往后找到比基准大的元素，放到刚才挖的坑2中
        }
        arr[left] = temp; //基准值填补到坑3中，准备分治递归快排
        sort(arr, low, left - 1);
        sort(arr, left + 1, high);
    }

}
