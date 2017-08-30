class DistinctSubsequences {
    
    //DP, O(ls*lt) time and O(lt) space
    //dp[l1][l2] is the number of distinct subsequences of s[0...l1-1] in t[0...l2-1]
    //dp[l1][l2] = 0 , if l1 < l2
    //             dp[l1-1][l2]
    //           = dp[l1-1][l2-1], if s[l1-1] == t[l2-1]
    public int numDistinct(String s, String t) {
        if (s == null || t == null) {
            return 0;
        }
        int ls = s.length();
        int lt = t.length();
        int[] dp = new int[lt + 1];
        dp[0] = 1;
        for (int l1 = 1; l1 <= ls; ++l1) {
            for (int l2 = Math.min(l1, lt); l2 >= 1; --l2) {
                if (s.charAt(l1-1) == t.charAt(l2-1)) {
                    dp[l2] += dp[l2-1];
                }
            }
        }
        return dp[lt];
    }
}