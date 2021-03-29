package chapter3;

public class RegexMatch_19 {

    static class Solution {

        public boolean match(String str, String pattern) {
            if (str == null || pattern == null) {
                return false;
            }

            str = str + '\0';
            pattern = pattern + '\0';

            return matchCore(str.toCharArray(), pattern.toCharArray(), 0, 0);
        }

        private boolean matchCore(char[] str, char[] pattern, int strIdx, int patIdx) {
            if (str[strIdx] == '\0' && pattern[patIdx] == '\0')
                return true;

            if (str[strIdx] != '\0' && pattern[patIdx] == '\0') {
                return false;
            }

            if (pattern[patIdx + 1] == '*') {
                if (pattern[patIdx] == str[strIdx]
                        || (pattern[patIdx] == '.' && str[strIdx] != '\0')) {
                    return matchCore(str, pattern, strIdx + 1, patIdx + 2)
                            || matchCore(str, pattern, strIdx + 1, patIdx)
                            || matchCore(str, pattern, strIdx, patIdx + 2);
                } else {
                    return matchCore(str, pattern, strIdx, patIdx + 2);
                }
            }

            if (pattern[patIdx] == str[strIdx] || (pattern[patIdx] == '.' && str[strIdx] != '\0')) {
                return matchCore(str, pattern, strIdx + 1, patIdx + 1);
            }

            return false;
        }
    }

    public static void main(String[] args) {
        System.out.println(new Solution().match("aaabcc", "a*bc"));
    }
}
