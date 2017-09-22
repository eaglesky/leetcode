class ReverseInteger {

    public int reverse(int x) {
        if (x == 0) {
            return 0;
        }
        int result = 0;
        for (; x != 0; x = x / 10) {
            int digit = x % 10;
            int absResult = Math.abs(result);
            if (absResult > Integer.MAX_VALUE / 10) {
                return 0;
            } else if (result == Integer.MAX_VALUE / 10) {
                if ((x < 0 && digit > 8)
                    || (x > 0 && digit > 7)){
                    return 0;
                }
            }
            result = result * 10 + digit;
        }
        return result;
    }

    //Since x can't overflow, when result == Integer.MAX_VALUE/10,
    //the next digit can only be 1 if exists.
    //https://discuss.leetcode.com/topic/5064/why-compared-with-214748364-in-the-solution
    public int reverse(int x) {
        if (x == 0) {
            return 0;
        }
        int result = 0;
        for (; x != 0; x = x / 10) {
            int digit = x % 10;
            int absResult = Math.abs(result);
            if (absResult > Integer.MAX_VALUE / 10) {
                return 0;
            } 
            result = result * 10 + digit;
        }
        return result;
    }
}