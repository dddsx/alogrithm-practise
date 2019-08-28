package sort;


/**
 * 这里的high取的都是a.length - 1，所以注意比较时临界值必须到达high，而不是<high
 */
public class Practise {

    static class BubbleSort implements Sortable {
        @Override
        public void sort(int[] a) {
            int len = a.length;
            for (int i = 0; i < len - 1; i++) {
                boolean resolve = true;
                for (int j = 0; j < len - i - 1; j++) {
                    if (a[j] > a[j + 1]) {
                        resolve = false;
                        swap(a, j, j + 1);
                    }
                }
                if (resolve) {
                    break;
                }
            }
        }
    }

    static class QuickSort implements Sortable {
        @Override
        public void sort(int[] a) {
            sort(a, 0, a.length - 1);
        }

        private void sort(int[] a, int low, int high) {
            if (low >= high)
                return;

            int pivot = partition(a, low, high);
            sort(a, low, pivot - 1);
            sort(a, pivot + 1, high);
        }

        private int partition(int[] a, int low, int high) {
            int left = low;
            int right = high;

            int key = a[low];

            while (left < right) {
                while (left < right && a[right] >= key) {
                    right--;
                }
                while (left < right && a[left] <= key) {
                    left++;
                }

                swap(a, left, right);
            }

            swap(a, low, left);
            return left;
        }
    }

    static class MergingSort implements Sortable {
        @Override
        public void sort(int[] a) {
            sort(a, 0, a.length - 1);
        }

        private void sort(int[] a, int low, int high) {
            if (low >= high) {
                return;
            }

            int mid = (low + high) / 2;
            sort(a, low, mid);
            sort(a, mid + 1, high);
            merge(a, low, mid, high);
        }

        private void merge(int[] a, int low, int mid, int high) {
            if (low >= high) {
                return;
            }

            int[] temp = new int[high - low + 1];

            int left = low;
            int right = mid + 1;
            int i = 0;

            // 注意，是"<="
            while (left <= mid && right <= high) {
                if (a[left] <= a[right]) {
                    temp[i++] = a[left++];
                } else {
                    temp[i++] = a[right++];
                }
            }

            while (left <= mid) {
                temp[i++] = a[left++];
            }
            while (right <= high) {
                temp[i++] = a[right++];
            }

            for (i = 0; i < temp.length; i++) {
                a[low + i] = temp[i];
            }
        }
    }

    private static void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    public static void main(String[] args) {
        Test.test(new MergingSort());
    }

}
