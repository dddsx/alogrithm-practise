package sort;


public class MergingSort {
    
    public static void sort(int[] arr, int left, int right) {
        if (left >= right) {
            return;
        }

        int mid = (left + right) / 2;
        sort(arr, left, mid); // 左边归并排序，使得左子序列有序
        sort(arr, mid + 1, right); // 右边归并排序，使得右子序列有序
        merge(arr, left, mid, right); // 合并两个子序列
    }
    
    private static void merge(int[] arr, int left, int mid, int right) {
        // ps：也可以从开始就申请一个与原数组大小相同的数组，因为重复new数组会频繁申请内存
        int[] temp = new int[right - left + 1];

        int i = left;
        // 当序列长度为偶数时，mid指向的是左序列最后一位；当序列长度为奇数时，mid指向的是序列的中间
        // 所以无论何种情况，mid+1指向的就是右序列的第一位
        int j = mid + 1;
        int k = 0;
        
        while (i <= mid && j <= right) {
            // 必须用"<="进行比较，保证排序稳定性
            if (arr[i] <= arr[j]) {
                temp[k++] = arr[i++];
            } else {
                temp[k++] = arr[j++];
            }
        }

        // 此时只有其中一种情况：左序列剩余元素或右序列剩余元素
        // 将左序列剩余元素填充进temp中(情况1)
        while (i <= mid) {
            temp[k++] = arr[i++];
        }
        // 将右序列剩余元素填充进temp中(情况2)
        while (j <= right) {
            temp[k++] = arr[j++];
        }
        
        // 将temp中的元素全部拷贝到原数组中
        for (i = 0; i < temp.length; i++) {
            arr[left + i] = temp[i];
        }
    }
    
}
