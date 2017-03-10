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
}