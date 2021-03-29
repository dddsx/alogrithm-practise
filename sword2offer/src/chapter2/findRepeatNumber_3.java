package chapter2;

public class findRepeatNumber_3 {

    static class Solution {
        public int findRepeatNumber(int[] nums) {
            for (int i = 0; i < nums.length; i++) {
                while (nums[i] != i) {
                    if (nums[i] == nums[nums[i]]) {
                        return nums[i];
                    }

                    int temp = nums[i];
                    nums[i] = nums[temp];
                    nums[temp] = temp;
                }
            }
            return -1;
        }
    }

    public static void main(String[] args) {
        System.out.println(new Solution().findRepeatNumber(new int[]{1, 2, 3, 5, 4, 0}));
    }
}
