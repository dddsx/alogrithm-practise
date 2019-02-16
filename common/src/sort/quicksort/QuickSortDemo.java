package sort.quicksort;

import java.util.Arrays;
import java.util.Random;

public class QuickSortDemo {

    public static void main(String[] args) {
        while (true) {
            int[] array = generateUseCase();
            quickSort(array, 0, array.length - 1);
            check(array);
            System.out.println(Arrays.toString(array));
        }
    }

    private static void quickSort(int[] arr, int start, int end){
        if(start >= end){
            return;
        }
        int mid = partition(arr, start, end);
        quickSort(arr, start, mid - 1);
        quickSort(arr, mid + 1, end);
    }

    private static int partition(int[] arr, int left, int right){
        int pivot = right;

        while (left < right) {
            while (left < right && arr[left] <= arr[pivot]) {
                left++;
            }

            while (left < right && arr[right] >= arr[pivot]) {
                right--;
            }

            if(left != right) {
                swap(arr, left, right);
            }
        }

        swap(arr, left, pivot);

        return left;
    }

    private static void swap(int[] arr, int l, int r){
        int temp = arr[l];
        arr[l] = arr[r];
        arr[r] = temp;
    }

    private static int[] generateUseCase(){
        Random random = new Random();
        int len = 3 + random.nextInt(8);
        int[] array = new int[len];
        for (int i = 0; i < len; i++) {
            array[i] = random.nextInt(11);
        }
        try {
            Thread.sleep(50);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return array;
    }

    private static void check(int[] arr){
        if(arr.length == 0){
            return;
        }
        int prev = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if(arr[i] < prev){
                throw new RuntimeException("测试用例不通过");
            }
            prev = arr[i];
        }
    }
}
