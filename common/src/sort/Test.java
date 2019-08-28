package sort;


import java.util.Arrays;
import java.util.Random;

public class Test {
    
    private final static int TESTCASE_ARRAY_MIN_LEN = 10;
    
    private final static int TESTCASE_ARRAY_MAX_LEN = 10;
    
    private final static int TESTCASE_MAX_ELE = 9;
    
    private final static int TESTCASE_INTERVAL = 50;
    
    /**
     * 排序枚举
     */
    private enum SortName {
        QUICKSORT1("快速排序-左右指针法"),
        QUICKSORT2("快速排序-挖坑法"),
        BUBBLESORT("冒泡排序"),
        INSERTSORT("直接插入排序-位移法"),
        SELECTIONSORT("选择排序"),
        MERGINGSORT("归并排序"),
        HEAPSORT("堆排序"),
        SHELLSORT("希尔排序"),
        RADIXSORT("基数排序");
        
        String name;
        
        SortName(String name) {
            this.name = name;
        }
    }
    
    private static int[] generateUseCase(){
        Random random = new Random();
        int len = TESTCASE_ARRAY_MIN_LEN + random.nextInt(TESTCASE_ARRAY_MAX_LEN - TESTCASE_ARRAY_MIN_LEN + 1);
        int[] array = new int[len];
        for (int i = 0; i < len; i++) {
            array[i] = random.nextInt(TESTCASE_MAX_ELE + 1);
        }
        
        try {
            Thread.sleep(TESTCASE_INTERVAL);
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
                throw new IllegalStateException("测试用例不通过");
            }
            prev = arr[i];
        }
    }

    private static void test(SortName sortName) {
        System.out.println("进行" + sortName.name() + "(" + sortName.name + ")" + "用例测试");
        
        while (true) {
            int[] array = generateUseCase();
            switch (sortName) {
                case QUICKSORT1:
                    QuickSort1.sort(array, 0, array.length - 1);
                    break;
                case QUICKSORT2:
                    QuickSort2.sort(array, 0, array.length - 1);
                    break;
                case BUBBLESORT:
                    BubbleSort.sort(array);
                    break;
                case INSERTSORT:
                    InsertSort.sort(array);
                    break;
                case SELECTIONSORT:
                    SelectionSort.sort(array);
                    break;
                case MERGINGSORT:
                    MergingSort.sort(array, 0, array.length - 1);
                    break;
                case HEAPSORT:
                    HeapSort.sort(array);
                    break;
                case SHELLSORT:
                    ShellSort.sort(array);
                    break;
                case RADIXSORT:
                    RadixSort.sort(array);
                    break;
                default:
                    System.exit(0);
            }
            check(array);
            System.out.println(Arrays.toString(array));
        }
    }

    public static void test(Sortable s) {
        while (true) {
            int[] array = generateUseCase();
            s.sort(array);
            check(array);
            System.out.println(Arrays.toString(array));
        }
    }

    private static void testAll() {
        // 使用这种实现方式异常会被吞掉，在调用Future#get()方法时异常才会抛出
        // executorService.submit(() -> test(value));
        for (SortName value : SortName.values()) {
            Thread thread = new Thread(() -> test(value));
            thread.setUncaughtExceptionHandler((t, e) -> {
                e.printStackTrace();
                System.exit(0);
            });
            thread.start();
        }
    }
    
    public static void main(String[] args) {
        test(SortName.MERGINGSORT);
        // testAll();
    }
    
}

@FunctionalInterface
interface Sortable {

    void sort(int[] a);
}