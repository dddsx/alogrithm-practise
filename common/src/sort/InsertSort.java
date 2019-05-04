package sort;

public class InsertSort {
    
    /**
     * 直接插入排序，需要额外的临时变量保存待插入数据
     */
    public static void sort(int[] a) {
        for (int i = 1; i < a.length; i++) {
            int j = i - 1;
            int temp = a[i];
            while (j >= 0 && a[j] > temp) {
                a[j + 1] = a[j];
                j--;
            }
            a[j + 1] = temp;
        }
    }
}
