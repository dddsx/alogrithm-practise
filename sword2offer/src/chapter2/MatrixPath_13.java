package chapter2;

public class MatrixPath_13 {

    static class Solution {

        public int movingCount(int m, int n, int k) {
            boolean[][] visited = new boolean[m][n];
            move(visited, k, 0, 0);

            int count = 0;
            for (int i = 0; i < visited.length; i++) {
                for (int j = 0; j < visited[0].length; j++) {
                    if (visited[i][j]) {
                        count++;
                    }
                }
            }
            return count;
        }

        private boolean move(boolean[][] visited, int k, int i, int j) {
            if (!canMove(visited, k, i, j)) {
                return false;
            }

            visited[i][j] = true;

            return move(visited, k, i + 1, j)
                    ||  move(visited, k, i, j - 1)
                    ||  move(visited, k, i - 1, j)
                    ||  move(visited, k, i , j + 1);
        }

        private boolean canMove(boolean[][] visited, int k, int i, int j) {
            if (!(i >= 0 && i < visited.length && j >= 0 && j < visited[0].length)) {
                return false;
            }
            if (visited[i][j]) {
                return false;
            }

            int sum = 0;
            do {
                sum += i % 10;
            } while ((i = i / 10) != 0);

            do {
                sum += j % 10;
            } while ((j = j / 10) != 0);

            return sum <= k;
        }
    }

    public static void main(String[] args) {
        new Solution().movingCount(1,2,1);
    }
}
