package chapter2;

public class MatrixPath_12 {

    static class Solution {
        public boolean exist(char[][] board, String word) {
            boolean[][] visited = new boolean[board.length][board[0].length];

            int wordLength = word.length();
            for (int row = 0; row < board.length; row++) {
                for (int col = 0; col < board[0].length; col++) {
                    if (hasPath(board, row, col, 0, word, visited)) {
                        return true;
                    }
                }
            }

            return false;
        }

        private boolean hasPath(char[][] board, int row, int col, int pathLength, String word, boolean[][] visited) {
            if (pathLength == word.length()) {
                return true;
            }

            boolean hasPath = false;
            if (row >= 0 && row < board.length && col >=0 && col < board[0].length
                    && board[row][col] == word.charAt(pathLength)
                    && !visited[row][col]) {
                pathLength++;
                visited[row][col] = true;
            }

            hasPath = hasPath(board, row, col - 1, pathLength, word, visited)
                    || hasPath(board, row - 1, col, pathLength, word, visited)
                    || hasPath(board, row, col + 1, pathLength, word, visited)
                    || hasPath(board, row + 1, col, pathLength, word, visited);

            if (!hasPath) {
                pathLength--;
                visited[row][col] = false;
            }

            return hasPath;
        }
    }
}
