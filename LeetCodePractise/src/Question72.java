public class Question72 {

    public static void main(String[] args) {
        Solution solution = new Solution1();
        System.out.println(solution.minDistance("horrewrw3rw4gggse", "rofwgegewefes"));
    }

    interface Solution {
        int minDistance(String word1, String word2);
    }

    /**
     * 暴力回溯法
     */
    static class Solution1 implements Solution {
        @Override
        public int minDistance(String word1, String word2) {
            if (word1.length() == 0 || word2.length() == 0) {
                return Math.max(word1.length(), word2.length());
            }
            if (word1.charAt(word1.length() - 1) == word2.charAt(word2.length() - 1)) {
                return minDistance(word1.substring(0, word1.length() - 1),
                        word2.substring(0, word2.length() - 1));
            }

            int s1 = minDistance(word1, word2.substring(0, word2.length() - 1));
            int s2 = minDistance(word1.substring(0, word1.length() - 1), word2);
            int s3 = minDistance(word1.substring(0, word1.length() - 1), word2.substring(0, word2.length() - 1));
            return 1 + Math.min(Math.min(s1, s2), s3);
        }
    }
}
