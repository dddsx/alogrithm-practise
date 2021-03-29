package chapter3;

public class PrintToMaxOfNDigits {

    static class Solution {

        void printToMaxOfNDigits(int n) {
            if (n <= 0)
                return;

            printRecursively("", n);

        }

        void printRecursively(String num, int digit) {
            if (digit == 0) {
                System.out.println(num);
                return;
            }

            for (int i = 0; i < 10; i++) {
                printRecursively(num + i, digit - 1);
            }
        }
    }

    public static void main(String[] args) {
        new Solution().printToMaxOfNDigits(8);
    }
}
