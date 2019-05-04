package sort;

public class BubbleSort {
    
    public static void sort(int[] array) {
        int length = array.length;
        
        for (int i = 0; i < length - 1; i++) {
            boolean hasSwap = false; // 如果某趟排序没有交换元素，则说明数组已经有序
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
    
    private static void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
}
