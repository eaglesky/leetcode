class DecodeWays2 {
    
    //O(n) time and O(1) space
    public int numDecodings(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        long pre2 = 0;
        long pre = 1;
        for (int i = 0; i < s.length(); ++i) {
            char c = s.charAt(i);
            long cur = 0;
            if (c == '*') {
                cur = 9 * pre;
            } else if (c != '0') {
                cur = pre;
            }
            if (i > 0) {
                int count = 1;
                char preC = s.charAt(i-1);
                if (preC == '1') {
                    if (c == '*') {
                        count = 9;
                    }
                } else if (preC == '2') {
                    if (c == '*') {
                        count = 6;
                    } else if (c > '6') {
                        count = 0;
                    }
                } else if (preC == '*') {
                    if (c == '*') {
                        count = 15;
                    } else if (c <= '6') {
                        count = 2;
                    }
                } else {
                    count = 0;
                }
                cur += count * pre2;
            }
            cur = cur % 1000000007;
            pre2 = pre;
            pre = cur;
        }
        return (int) pre;
    }
}