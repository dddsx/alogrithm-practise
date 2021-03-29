package chapter2;

import java.util.HashMap;
import java.util.Map;

public class Fibonacci_10 {

    static class Solution {

        private Map<Integer, Integer> caches = new HashMap<>();

        {
            caches.put(0, 0);
            caches.put(1, 1);
        }

        public int fib(int n) {
            Integer cache = caches.get(n);
            if (cache != null) {
                return cache;
            }

            int result = fib(n - 1) + fib(n - 2);
            caches.put(n, result);
            return result;
        }

        public int fibWithoutCache(int n) {
            if (n < 2) {
                return n;
            }
            return fibWithoutCache(n - 1) + fibWithoutCache(n - 2);
        }
    }

    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        System.out.println(new Solution().fib(45) % 1000000007);
        System.out.println(System.currentTimeMillis() - start);

        start = System.currentTimeMillis();
        System.out.println(new Solution().fibWithoutCache(45) % 1000000007);
        System.out.println(System.currentTimeMillis() - start);
    }
}
