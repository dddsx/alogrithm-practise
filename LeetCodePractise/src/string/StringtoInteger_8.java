package string;

public class StringtoInteger_8 {
    
    public static void main(String[] args) {
        // System.out.println(new Solution().myAtoi(" "));
        System.out.println(Integer.parseInt("312", 10));
    }
    
    private static class Solution {
        
        public int myAtoi(String str) {
            char[] chars = str.toCharArray();
            int sign = 1;
            int base = 0;
            int i = 0;
            while (i < str.length() && chars[i] == ' ') {
                i++;
            }
            if (i >= str.length()) {
                return 0;
            }
            if (chars[i] == '-' || chars[i] == '+') {
                sign = 1 - 2 * ((chars[i++] == '-') ? 1 : 0);
            }
            while (i < str.length() && chars[i] >= '0' && chars[i] <= '9') {
                if (base > Integer.MAX_VALUE / 10
                        || (base == Integer.MAX_VALUE / 10 && chars[i] - '0' > 7)) {
                    if (sign == 1) {
                        return Integer.MAX_VALUE;
                    } else {
                        return Integer.MIN_VALUE;
                    }
                }
                base = 10 * base + (chars[i++] - '0');
            }
            return base * sign;
        }
    }
}
