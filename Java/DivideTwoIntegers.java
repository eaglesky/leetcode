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

    //Second try, better implementation
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
        int quotient = 0; //Quotient won't overlow in this case
        for(; absDividend >= absDivisor;) {
            long add = absDivisor;
            for (int count = 1; absDividend >= add; add = add << 1, count = count << 1) {
                absDividend -= add;
                quotient += count;
            }
        }
        return isNegative ? -quotient : quotient;
    }

    //Third try, add more pre-processing
    public int divide(int dividend, int divisor) {
        if (divisor == 0) {
            return dividend >= 0 ? Integer.MAX_VALUE : Integer.MIN_VALUE;
        } else if (divisor == -1) {
            return dividend == Integer.MIN_VALUE ? Integer.MAX_VALUE : -dividend;
        } else if (divisor == 1) {
            return dividend;
        }
        boolean haveSameSigns = (dividend ^ divisor) >= 0;
        long absDividend = Math.abs((long) dividend);
        long absDivisor = Math.abs((long) divisor);
        int result = 0;
        for (long dividendLeft = absDividend; dividendLeft >= absDivisor;) {
            long count = 1;
            long curDivisor = absDivisor;
            for (; dividendLeft >= curDivisor;) {
                dividendLeft -= curDivisor;
                result += count;
                curDivisor  = curDivisor << 1;
                count = count << 1;
            }
        }
        return haveSameSigns ? result : -result;
    }

    public static void main(String[] args) {
        DivideTwoIntegers dti = new DivideTwoIntegers();

        int dividend = Integer.parseInt(args[0]);
        int divisor = Integer.parseInt(args[1]);

        System.out.println(dividend + " / " + divisor + " = " + dti.divide(dividend, divisor));
    }
}