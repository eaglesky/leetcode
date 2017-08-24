class LongestPalinSubsequence {
    
    //O(n^2) time and O(n) space
    //dp[i][j] is the length of lps in s[i...j].
    //dp[i][j] = dp[i+1][j-1] + 2, if s[i] == s[j]
    //        or max(dp[i][j-1], dp[i+1][j]), if otherwise
    public int longestPalindromeSubseq(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        int[] dp = new int[s.length()];
        for (int i = s.length() - 1; i >= 0; --i) {
            int pre = 0;
            dp[i] = 1;
            for (int j = i + 1; j < s.length(); ++j) {
                int nextPre = dp[j];
                if (s.charAt(i) == s.charAt(j)) {
                    dp[j] = pre + 2;
                } else {
                    dp[j] = Math.max(dp[j], dp[j-1]);
                }
                pre = nextPre;
            }
        }
        return dp[s.length() - 1];
    }
}