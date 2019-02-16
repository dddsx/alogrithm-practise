package string;


public class ZigZagConversion_6 {
    public static void main(String[] args) {
        System.out.println(new Solution().convert("PAYPALISHIRING", 3));
    }

    static class Solution {
        public String convert(String s, int numRows) {
            int len = s.length();
            char[] charArr = s.toCharArray();
            StringBuilder[] sbs = new StringBuilder[numRows];
            for (int i = 0; i < sbs.length; i++) {
                sbs[i] = new StringBuilder();
            }

            int i = 0;
            while (i < len){
                for (int currRow = 0; currRow < numRows && i < len; currRow++) {
                    sbs[currRow].append(charArr[i++]);
                }
                for (int currRow = numRows - 2; currRow > 0 && i < len; currRow--){
                    sbs[currRow].append(charArr[i++]);
                }
            }

            StringBuilder result = new StringBuilder();
            for (StringBuilder sb : sbs) {
                result.append(sb.toString());
            }

            return result.toString();
        }
    }
}
