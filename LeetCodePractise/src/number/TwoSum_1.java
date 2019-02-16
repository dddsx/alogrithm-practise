package number;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class TwoSum_1 {
    public static void main(String[] args) {
        int[] result = new Solution().twoSum(new int[]{3, 2, 4}, 6);
        System.out.println(Arrays.toString(result));
    }

    static class Solution {
        public int[] twoSum(int[] nums, int target) {
            Map<Integer, Integer> map = new HashMap<>();
            int len = nums.length;
            Integer key1 = -1, key2 = -1;

            for (int i = 0; i < len; i++) {
                map.put(nums[i], i);
            }
            for (int i = 0; i < len - 1; i++) {
                key2 = map.get(target - nums[i]);
                if(key2 != null && !key2.equals(i)){
                    key1 = i;
                    break;
                }
            }

            return new int[]{key1, key2};
        }
    }
}