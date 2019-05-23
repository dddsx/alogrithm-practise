package number;

public class ReverseInteger_7 {
    
    public static void main(String[] args) {
        System.out.println(new Solution().reverse(-1534236469));
        System.out.println(new Solution().reverse(2147483647));
        System.out.println(new Solution().reverse(7463847412L));
        System.out.println("---------------------------------");
        System.out.println(0b00000000000000000000000000000000);
        System.out.println(0b01111111111111111111111111111111);
        System.out.println(0b11111111111111111111111111111111);
        System.out.println(0b10000000000000000000000000000001);
        System.out.println(0b10000000000000000000000000000000);
    }
    
    static class Solution {
        
        public int reverse(long x) {
            int rev = 0;
            while (x != 0) {
                long pop = x % 10;
                x /= 10;
                if (rev > Integer.MAX_VALUE / 10 || (rev == Integer.MAX_VALUE / 10 && pop > 7))
                    return 0;
                if (rev < Integer.MIN_VALUE / 10 || (rev == Integer.MIN_VALUE / 10 && pop < -8))
                    return 0;
                rev = (int) (rev * 10 + pop);
            }
            return rev;
        }
    }
}
