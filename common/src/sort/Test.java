package sort;


import sort.unusual.BeadSort;

import java.util.Arrays;
import java.util.Random;

public class Test {
    
    private final static int TESTCASE_ARRAY_MIN_LEN = 10;
    
    private final static int TESTCASE_ARRAY_MAX_LEN = 10;

    private final static int TESTCASE_MIN_ELE = 1;

    private final static int TESTCASE_MAX_ELE = 9;
    
    private final static int TESTCASE_INTERVAL = 50;
    
    /**
     * 排序枚举
     */
    private enum SortEnum {
        QUICKSORT1("快速排序-左右指针法", QuickSort1.class),
        QUICKSORT2("快速排序-挖坑法", QuickSort2.class),
        BUBBLESORT("冒泡排序", BubbleSort.class),
        INSERTSORT("直接插入排序-位移法", InsertSort.class),
        SELECTIONSORT("选择排序", SelectionSort.class),
        MERGINGSORT("归并排序", MergingSort.class),
        HEAPSORT("堆排序", HeapSort.class),
        SHELLSORT("希尔排序", ShellSort.class),
        RADIXSORT("基数排序", RadixSort.class),
        BEADSORT("珠排序", BeadSort.class);
        
        String name;

        Class<? extends Sortable> clazz;
        
        SortEnum(String name, Class<? extends Sortable> clazz) {
            this.name = name;
            this.clazz = clazz;
        }
    }
    
    private static int[] generateUseCase(){
        Random random = new Random();
        int len = TESTCASE_ARRAY_MIN_LEN + random.nextInt(TESTCASE_ARRAY_MAX_LEN - TESTCASE_ARRAY_MIN_LEN + 1);
        int[] array = new int[len];
        for (int i = 0; i < len; i++) {
            array[i] = TESTCASE_MIN_ELE + random.nextInt(TESTCASE_MAX_ELE - TESTCASE_MIN_ELE + 1);
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

    private static void test(SortEnum sortEnum) throws Throwable {
        System.out.println("进行" + sortEnum.name() + "(" + sortEnum.name + ")" + "用例测试");
        Sortable sortable = sortEnum.clazz.newInstance();
        while (true) {
            int[] array = generateUseCase();
            int[] sourceArray = new int[array.length];
            System.arraycopy(array, 0, sourceArray, 0, array.length);
            sortable.sort(array);
            try {
                check(array);
            } catch (Exception e) {
                System.out.println("测试用例不通过:" + Arrays.toString(sourceArray));
                System.exit(0);
            }
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
        for (SortEnum value : SortEnum.values()) {
            Thread thread = new Thread(() -> {
                try {
                    test(value);
                } catch (Throwable throwable) {
                    throwable.printStackTrace();
                }
            });
            thread.setUncaughtExceptionHandler((t, e) -> {
                e.printStackTrace();
                System.exit(0);
            });
            thread.start();
        }
    }
    
    public static void main(String[] args) throws Throwable {
        // test(SortEnum.MERGINGSORT);
        testAll();
    }
    
}

