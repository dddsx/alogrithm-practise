package sort;

public class InsertSort implements Sortable {
    
    /**
     * 直接插入排序，需要额外的临时变量保存待插入数据
     * 其理念是前面的元素都已排序好，只需要找到已排序的元素小于或者等于新元素的位置
     */
    public void sort(int[] a) {
        for (int i = 1; i < a.length; i++) {
            int j = i - 1;
            int temp = a[i];
            // "a[j] > temp"保证了稳定性
            while (j >= 0 && a[j] > temp) {
                a[j + 1] = a[j];
                j--;
            }
            a[j + 1] = temp;
        }
    }
}
