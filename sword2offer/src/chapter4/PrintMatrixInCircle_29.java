package chapter4;

import java.util.Arrays;

public class PrintMatrixInCircle_29 {

    static class Solution {
        public int[] spiralOrder(int[][] matrix) {
            if (matrix == null || matrix.length == 0) {
                return new int[0];
            }

            int row = matrix.length;
            int col = matrix[0].length;
            int[] result = new int[row * col];
            int index = 0;
            for (int i = 0, j = 0; i < (row + 1) / 2 && j < (col + 1) / 2; i++, j++) {

                for (int k = j; k < col - j - 1; k++) {
                    result[index++] = matrix[i][k];
                }

                for (int k = i; k < row - i - 1; k++) {
                    result[index++] = matrix[k][col - j - 1];
                }

                for (int k = col - j - 1; k > j; k--) {
                    result[index++] = matrix[row - i - 1][k];
                }

                for (int k = row - i - 1; k > i; k--) {
                    result[index++] = matrix[k][j];
                }
            }

            if ((row & 1) == 1 && (col & 1) == 1) {
                result[index++] = matrix[row / 2][col / 2];
            }
            return result;
        }
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(new Solution().spiralOrder(new int[][]{{6, 9, 7}})));
    }
}
