package sort;

/**
 * 冒泡排序，时间复杂度O(n²)
 */
public class BubbleSort implements Sortable {
    
    public void sort(int[] array) {
        int length = array.length;
        
        for (int i = 0; i < length - 1; i++) {
            boolean hasSwap = false; // 优化：如果某趟排序没有交换元素，则说明数组已经有序
            for (int j = 0; j < length - 1 - i; j++) {
                if (array[j] > array[j + 1]) {
                    swap(array, j, j + 1);
                    hasSwap = true;
                }
            }
            
            if (!hasSwap) {
                return;
            }
        }
    }
    
    private void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
}
