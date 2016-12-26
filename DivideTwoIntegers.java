public class DivideTwoIntegers {

    //Dividend = divisor * quotient
    //         = divisor * (2^dk + 2^dk_1 + 2^dk_2 + .. + 2^d0)
    //         = divisor * 2^dk + divisor * 2^dk_1 + .. + divisor * 2^d0
    // divisor * 2^dk can be calculated by shifting divisor to the left by dk bits
    public int divide(int dividend, int divisor) {
        boolean isNegative = (dividend ^ divisor) < 0;
        if (divisor == 0) {
            return isNegative ? Integer.MIN_VALUE : Integer.MAX_VALUE;
        }
        if (dividend == Integer.MIN_VALUE && divisor == -1) {
            return Integer.MAX_VALUE;
        }
        long absDividend = Math.abs((long)dividend);
        long absDivisor = Math.abs((long)divisor);
        long quotient = 0;
        for (long sum = 0; absDividend - sum >= absDivisor; ) {
            long add = absDivisor;
            int expo = 0;
            for(; absDividend - sum - add >= add; add = add << 1, expo++);
            sum += add;
            quotient += 1L << expo;
        }
        return isNegative ? (int)(-quotient) : (int)quotient;
    }

    public static void main(String[] args) {
        DivideTwoIntegers dti = new DivideTwoIntegers();

        int dividend = Integer.parseInt(args[0]);
        int divisor = Integer.parseInt(args[1]);

        System.out.println(dividend + " / " + divisor + " = " + dti.divide(dividend, divisor));
    }
}