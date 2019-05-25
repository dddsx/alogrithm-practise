package dp;

public class MaximumSubarray_53 {
    
     static class Solution {
        public int maxSubArray(int[] nums) {
            if (nums == null || nums.length == 0) {
                return 0;
            }
            int n = nums.length;
            int[] f = new int[n + 1];
            for (int i = 1; i <= n; ++i) {
                f[i] = nums[i - 1];
                if (f[i - 1] > 0) {
                    f[i] += f[i - 1];
                }
            }
            int max = Integer.MIN_VALUE;
            for (int i = 1; i <= n; ++i) {
                max = Math.max(max, f[i]);
            }
            return max;
        }
    }
    
    public static void main(String[] args) {
        int[] nums = new int[]{-2, 1, -3, 4, -1, 2, 1, -5, 4};
        System.out.println(new Solution().maxSubArray(nums));
    }
}
