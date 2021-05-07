package sort;

/**
 * 希尔排序，又称缩小增量排序，时间复杂度O(n1.25)
 * 增量gap取法有各种方案，如gap=[n/2]，gap=[gap/2]或gap=[n/3]+1，gap=[gap/3]+1
 */
public class ShellSort implements Sortable {
    
    public void sort(int[] arr) {
        int gap = arr.length / 2;
        for (; gap > 0; gap = gap / 2) {
            for (int j = 0; (j + gap) < arr.length; j++) { //不断缩小gap，直到1为止
                for (int k = 0; (k + gap) < arr.length; k += gap) { //使用当前gap进行组内插入排序
                    if (arr[k] > arr[k + gap]) { //交换操作
                        arr[k] = arr[k] + arr[k + gap];
                        arr[k + gap] = arr[k] - arr[k + gap];
                        arr[k] = arr[k] - arr[k + gap];
                    }
                }
            }
        }
    }
}
