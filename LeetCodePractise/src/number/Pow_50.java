package number;


public class Pow_50 {
    
    static class Solution {
        
        public double myPow(double x, int n) {
            if (n == 0) return 1;
            if (n == 1) return x;
            if (n == -1) return 1 / x;
            if (n == 2) return x * x;
            return myPow(myPow(x, n / 2), 2) * myPow(x, n % 2);
        }
    }
    
    public static void main(String[] args) {
        System.out.println(new Solution().myPow(3, 11));
    }
}
