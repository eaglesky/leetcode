public class DecodeWays {
    
    //DP solution, O(n) time and O(1) space
    public int numDecodings(String s) {
        if (s.isEmpty()) {
            return 0;
        }
        int prev2 = 1, prev = 1;
        for (int i = 0; i < s.length(); ++i) {
            char c = s.charAt(i);
            int cur = (c != '0') ? prev : 0;
            if (i >= 1) {
                int curInt = Integer.parseInt(s.substring(i-1, i+1));
                if (curInt >= 10 && curInt <= 26) {
                    cur += prev2;
                }
            }
            prev2 = prev;
            prev = cur;
        }
        return prev;
    }

    //Second try, using length representation.
    public int numDecodings(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        int pre = 1;
        int pre2 = 0;
        for (int l = 1; l <= s.length(); ++l) {
            int cur = 0;
            char c = s.charAt(l - 1);
            if (c != '0') {
                cur += pre;
            }
            if (l >= 2) {
                char preC = s.charAt(l - 2);
                int num = (preC - '0') * 10 + (c - '0');
                if (num >= 10 && num <= 26) {
                    cur += pre2;
                }
            }
            pre2 = pre;
            pre = cur;
        }
        return pre;
    }
}