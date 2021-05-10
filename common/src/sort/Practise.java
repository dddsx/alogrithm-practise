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

        private void sort(int[] a, int l, int r) {
            if (l >= r) {
                return;
            }
            int pivot = partition(a, l, r);
            sort(a, l, pivot - 1);
            sort(a, pivot + 1, r);
        }

        private int partition(int[] a, int l, int r) {
            int key = a[l];
            int i = l;
            int j = r;
            while (i < j) {
                while (a[j] > key && i < j) {
                    j--;
                }
                while (a[i] <= key && i < j) {
                    i++;
                }
                swap(a, i, j);
            }
            swap(a, l, i);
            return i;
        }

        private void swap(int[] a, int i, int j) {
            if (i == j) {
                return;
            }
            int temp = a[j];
            a[j] = a[i];
            a[i] = temp;
        }

    }

    static class MergingSort implements Sortable {
        @Override
        public void sort(int[] a) {
            sort(a, 0, a.length - 1);
        }

        private void sort(int[] a, int l, int r) {
            if (l == r) {
                return;
            }

            int mid = (l + r) / 2;
            sort(a, l ,mid);
            sort(a, mid + 1, r);
            merge(a, l, mid, r);
        }

        private void merge(int[] a, int l, int mid, int r) {
            int[] lArr = new int[mid - l + 1];
            System.arraycopy(a, 0, lArr, 0, mid - l + 1);
            int[] rArr = new int[r - mid];
            System.arraycopy(a, mid + 1, rArr, 0, r - mid);

            int i = 0, j = 0, k = l;
            while (i < lArr.length && j < rArr.length) {
                if (lArr[i] <= rArr[j]) {
                    a[k++] = lArr[i];
                    i++;
                } else {
                    a[k++] = rArr[j];
                    j++;
                }
            }

            while (i < lArr.length) {
                a[k++] = lArr[i++];
            }

            while (j < rArr.length) {
                a[k++] = rArr[j++];
            }
        }
    }

    /**
     * 力扣原题，3种颜色排序，也就是排序元素0, 1, 2
     */
    static class ThreeSort implements  Sortable {

        @Override
        public void sort(int[] a) {
            int p0 = 0; // 表示下一个用于插入0的位置
            int p1 = 0; // 表示下一个用于插入1的位置
            for (int i = 0; i < a.length; i++) {
                if (a[i] == 0) {
                    int temp = a[i];
                    a[i] = a[p0];
                    a[p0] = temp;
                    if (p1 > p0) {
                        temp = a[i];
                        a[i] = a[p1];
                        a[p1] = temp;
                    }
                    p0++;
                    p1++;
                } else if (a[i] == 1) {
                    int temp = a[i];
                    a[i] = a[p1];
                    a[p1] = temp;
                    p1++;
                }
            }
        }
    }

    private static void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    public static void main(String[] args) {
        Test.test(new ThreeSort());
    }

}
