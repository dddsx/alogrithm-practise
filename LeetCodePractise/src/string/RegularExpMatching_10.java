package string;

public class RegularExpMatching_10 {
    
    public static void main(String[] args) {
        System.out.println(new Solution().isMatch("gewgeab", ".*ab"));
    }
    
    static class Solution {
        
        public boolean isMatch(String text, String pattern) {
            if (pattern.isEmpty()) {
                return text.isEmpty();
            }
            
            boolean firstMatch = (!text.isEmpty() &&
                    (pattern.charAt(0) == text.charAt(0) || pattern.charAt(0) == '.'));
            if (pattern.length() >= 2 && pattern.charAt(1) == '*') {
                return (isMatch(text, pattern.substring(2)) ||
                        (firstMatch && isMatch(text.substring(1), pattern)));
            } else {
                return firstMatch && isMatch(text.substring(1), pattern.substring(1));
            }
        }
    }
}
