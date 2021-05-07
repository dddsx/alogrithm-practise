package sort.unusual;


import sort.Sortable;

/**
 * 珠排序，https://www.cnblogs.com/kkun/archive/2011/11/23/2260301.html
 * 暂不支持0元素
 */
public class BeadSort implements Sortable {

    public void sort(int[] arr) {
        int len = arr.length;
        int maxNum = max(arr); // 获取待排序数组的最大值
        int[][] bead = new int[arr.length][maxNum];

        // 挂珠子
        for (int i = 0; i < len; i++) {
            int k = arr[i];
            for (int j = 0; j < k; j++) {
                bead[i][j] = 1;
            }
        }

        // 自由落体
        for (int j = 1; j < maxNum; j++) {
            int k = 0;
            for (int i = 0; i < len; i++) {
                if (bead[i][j] == 1) {
                    k++;
                }
            }
            for (int i = 0; i < len; i++) {
                if (len - i <= k) {
                    bead[i][j] = 1;
                } else {
                    bead[i][j] = 0;
                }
            }
        }

        // 输出结果
        for (int i = 0; i < len; i++) {
            int[] row = bead[i];
            int j = 0;
            for (; j < maxNum; j++) {
                if (row[j] == 0) {
                    break;
                }
            }
            arr[i] = j;
        }
    }

    private int max(int[] arr) {
        int max = Integer.MIN_VALUE;
        for (int i : arr) {
            if (i > max) {
                max = i;
            }
        }
        return max;
    }

}
