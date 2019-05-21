package string;


import java.util.HashMap;
import java.util.Map;

public class RomanToInteger_13 {
    
    public static void main(String[] args) {
        System.out.println(new Solution().romanToInt("MCMXCIV"));
    }

    static class Solution {
    
        static char I = 'I';
        static char V = 'V';
        static char X = 'X';
        static char L = 'L';
        static char C = 'C';
        static char D = 'D';
        static char M = 'M';
    
        static Map<Character, Integer> values = new HashMap<>();
        static {
            values.put(I, 1);
            values.put(V, 5);
            values.put(X, 10);
            values.put(L, 50);
            values.put(C, 100);
            values.put(D, 500);
            values.put(M, 1000);
        }
        
        public int romanToInt(String s) {
            if (s.length() < 1) {
                throw new IllegalArgumentException();
            }
            if (s.length() == 1) {
                return values.get(s.charAt(0));
            }
        
            int val = 0;
            char[] chars = s.toCharArray();
            for (int i = 0; i < chars.length;) {
                if (i == chars.length - 1) {
                    val += values.get(chars[i]);
                    break;
                }
                
                char p = chars[i];
                char q = chars[i + 1];
                if (values.get(q) > values.get(p)) {
                    val += (values.get(q) - values.get(p));
                    i = i + 2;
                } else {
                    val += values.get(p);
                    ++i;
                }
            }
        
            return val;
        }
    }
}