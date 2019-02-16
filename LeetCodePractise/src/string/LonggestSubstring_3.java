package string;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class LonggestSubstring_3 {
    public static void main(String[] args) {
        //System.out.println(new Solution1().lengthOfLongestSubstring(" "));
        System.out.println(new Solution2().lengthOfLongestSubstring("acbdbaacbb"));
        //System.out.println(new Solution3().lengthOfLongestSubstring("abcabcbb"));
    }

    static class Solution1 {
        public int lengthOfLongestSubstring(String s) {
            if(s == null){
                return -1;
            }
            Set<Character> repeat = new HashSet<>();
            StringBuilder sb = new StringBuilder();
            int maxLength = 0;
            String maxSubString = null;
            for (int i = 0; i < s.length(); i++){
                for (int j = i; j < s.length(); j++){
                    char c = s.charAt(j);
                    if(!repeat.contains(c)) {
                        repeat.add(s.charAt(j));
                        sb.append(c);
                    } else {
                        String stopSubStr = sb.toString();
                        repeat.clear();
                        sb = new StringBuilder();
                        if(stopSubStr.length() > maxLength){
                            maxSubString = stopSubStr;
                            maxLength = stopSubStr.length();
                        }
                        break;
                    }
                    if(j == s.length() - 1){
                        String stopSubStr = sb.toString();
                        repeat.clear();
                        sb = new StringBuilder();
                        if(stopSubStr.length() > maxLength){
                            maxSubString = stopSubStr;
                            maxLength = stopSubStr.length();
                        }
                    }
                }
            }
            System.out.println(maxSubString);
            return maxLength;
        }
    }

    static class Solution2 {
        public int lengthOfLongestSubstring(String s) {
            int n = s.length();
            Set<Character> set = new HashSet<>();
            int ans = 0, i = 0, j = 0;
            while (i < n && j < n) {
                // try to extend the range [i, j]
                if (!set.contains(s.charAt(j))){
                    set.add(s.charAt(j++));
                    ans = Math.max(ans, j - i);
                }
                else {
                    set.remove(s.charAt(i++));
                }
            }
            return ans;
        }
    }

    static class Solution3{
        public int lengthOfLongestSubstring(String s) {
            int n = s.length(), ans = 0;
            Map<Character, Integer> map = new HashMap<>(); // current index of character
            // try to extend the range [i, j]
            for (int j = 0, i = 0; j < n; j++) {
                if (map.containsKey(s.charAt(j))) {
                    i = Math.max(map.get(s.charAt(j)), i);
                }
                ans = Math.max(ans, j - i + 1);
                map.put(s.charAt(j), j + 1);
            }
            return ans;
        }
    }
}