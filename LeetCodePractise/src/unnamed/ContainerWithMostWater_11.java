package unnamed;

public class ContainerWithMostWater_11 {
    
    static class Solution {
    
        public int maxArea(int[] height) {
            int len = height.length;
            int i = 0;
            int j = len - 1;
            int maxArea = 0;
            
            while (i < j) {
                maxArea = Math.max(maxArea, Math.min(height[i], height[j]) * (j - i));
                if (height[i] < height[j]) {
                    i++;
                } else if (height[i] > height[j]) {
                    j--;
                } else {
                    if (height[i + 1] > height[j - 1]) {
                        i++;
                    } else if (height[i + 1] < height[j - 1]) {
                        j--;
                    } else {
                        i++;
                    }
                }
            }
            return maxArea;
        }
    }
    
    public static void main(String[] args) {
        System.out.println(new Solution().maxArea(new int[]{1, 8, 6, 2, 5, 4, 8, 3, 7}));
    }
}
