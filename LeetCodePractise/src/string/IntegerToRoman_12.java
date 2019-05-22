package string;

import java.util.HashMap;
import java.util.Map;

public class IntegerToRoman_12 {
    
    public static void main(String[] args) {
        System.out.println(new Solution().intToRoman(1996));
    }
    
    static class Solution {
    
        static char I = 'I';
        static char V = 'V';
        static char X = 'X';
        static char L = 'L';
        static char C = 'C';
        static char D = 'D';
        static char M = 'M';
        
        static Map<Integer, Character> romanNums = new HashMap<>();
        static {
            romanNums.put(1, I);
            romanNums.put(5, V);
            romanNums.put(10, X);
            romanNums.put(50, L);
            romanNums.put(100, C);
            romanNums.put(500, D);
            romanNums.put(1000, M);
        }
        
        public String intToRoman(int num) {
            StringBuilder result = new StringBuilder();
            
            int thousandNum = num / 1000;
            while (thousandNum-- > 0) {
                result.append(romanNums.get(1000));
            }
            
            num = num % 1000;
            int hundredNum = num / 100;
            if (hundredNum > 0) {
                if (hundredNum == 9) {
                    result.append(C).append(M);
                } else if (hundredNum == 4) {
                    result.append(C).append(D);
                } else {
                    if (hundredNum >= 5) {
                        result.append(romanNums.get(500));
                        hundredNum = hundredNum - 5;
                    }
                    while (hundredNum-- > 0) {
                        result.append(romanNums.get(100));
                    }
                }
            }
            
            num = num % 100;
            int decadeNum = num / 10;
            if (decadeNum > 0) {
                if (decadeNum == 9) {
                    result.append(X).append(C);
                } else if (decadeNum == 4) {
                    result.append(X).append(L);
                } else {
                    if (decadeNum >= 5) {
                        result.append(romanNums.get(50));
                        decadeNum = decadeNum - 5;
                    }
                    while (decadeNum-- > 0) {
                        result.append(romanNums.get(10));
                    }
                }
            }
    
            num = num % 10;
            int unitNum = num;
            if (unitNum > 0) {
                if (unitNum == 9) {
                    result.append(I).append(X);
                } else if (unitNum == 4) {
                    result.append(I).append(V);
                } else {
                    if (unitNum >= 5) {
                        result.append(romanNums.get(5));
                        unitNum = unitNum - 5;
                    }
                    while (unitNum-- > 0) {
                        result.append(romanNums.get(1));
                    }
                }
            }
            
            return result.toString();
        }
    }
}
