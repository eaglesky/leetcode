public class Pow {
    
    //Recursive binary search. O(logn) time and O(logn) space
    //The case when x is zero and n is non-negative is also taken care of.
    //The case when x is zero and n is negative is assumed not possible.
    public double myPow0(double x, int n) {
        if (n == 0) {
            return 1;
        } else if (n < 0) {
            return 1.0 / (myPow0(x, -(n+1)) * x); //Pay attention to the overflow when n = Integer.MIN_VALUE
        }
        double half = myPow0(x, n / 2);
        return (n % 2 == 0) ? half * half : half * half * x;
    }
    
    //Iterative solution(Best). O(logn) time and O(1) space.
    //if n == (100101)b.
    //then x^n == x^(100000)b * x^(100)b * x^(1)b
    //Use cur to record the value of x^(100..0)b, and result to record the final result.
    //Keep shifting n to the left and multiple the result by cur when the rightmost digit is 1.
    
    //The case when x is zero and n is non-negative is also taken care of.
    //The case when x is zero and n is negative is assumed not possible.
    public double myPow(double x, int n) {
        if (n < 0) {
            return 1.0 / (myPow0(x, -(n+1)) * x); //Pay attention to the overflow when n = Integer.MIN_VALUE
        }
        double result = 1;
        double cur = x;
        for (; n > 0; n = n >> 1) {
            if ((n & 1) > 0) {
                result *= cur;
            }
            cur = cur * cur;
        }
        return result;
    }
}