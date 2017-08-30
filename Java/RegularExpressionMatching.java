import java.util.*;

public class RegularExpressionMatching {
    
    //DP solution, O(ls*lp*ls) time and O(ls) space
    public boolean isMatch0(String s, String p) {
        boolean[] pre2 = new boolean[s.length() + 1];
        boolean[] pre = new boolean[s.length() + 1];
        pre[0] = true;
        for (int lp = 1; lp <= p.length(); lp++) {
            for (int ls = s.length(); ls >= 0; ls--) {
                if (p.charAt(lp-1) == '.') {
                    pre2[ls] = ls > 0 && pre[ls-1];
                } else if (p.charAt(lp-1) == '*') {
                    if (p.charAt(lp-2) == '.') {
                        for (int i = ls; i >= 0; --i) {
                            if (pre2[i]) {
                                pre2[ls] = true;
                                break;
                            }
                        }
                    } else {
                        if (!pre2[ls]) {
                            int i = ls - 1;
                            for (; i >= 0 && s.charAt(i) == p.charAt(lp-2); --i) {
                                if (pre2[i]) {
                                    pre2[ls] = true;
                                    break;
                                }
                            }
                        }
                    }
                } else {
                    pre2[ls] = ls > 0 && (p.charAt(lp-1) == s.charAt(ls-1)) && pre[ls-1];
                }
            }
            boolean[] temp = pre2;
            pre2 = pre;
            pre = temp;
        }
        return pre[s.length()];
    }

    //DP solution, O(ls*lp) time and O(ls) space
    public boolean isMatch(String s, String p) {
        boolean[] pre2 = new boolean[s.length() + 1];
        boolean[] pre = new boolean[s.length() + 1];
        boolean[] cur = new boolean[s.length() + 1];
        pre[0] = true;
        for (int lp = 1; lp <= p.length(); lp++) {
            for (int ls = 0; ls <= s.length(); ls++) {
                if (p.charAt(lp-1) == '.') {
                    cur[ls] = ls > 0 && pre[ls-1];
                } else if (p.charAt(lp-1) == '*') {
                    cur[ls] = lp > 1 
                    && (pre2[ls] 
                        || (ls > 0 && (p.charAt(lp-2) == '.' || p.charAt(lp-2) == s.charAt(ls-1)) && cur[ls-1]));
                } else {
                    cur[ls] = ls > 0 && (p.charAt(lp-1) == s.charAt(ls-1)) && pre[ls-1];
                }
            }
            boolean[] temp = pre2;
            pre2 = pre;
            pre = cur;
            cur = temp;
        }
        return pre[s.length()];
    }

    //O(ls * lp) time and O(lp) space, using one array.
    //Assuming p is a valid pattern!
    //f[l1][l2] represents if s(0...l1-1) and p(0...l2-1) are matched.
    //f[l1][l2] = (p[l2-1]=='.' || p[l2-1]==s[l1-1]) && f[l1-1][l2-1], if p[l2-1]!='*'
    //         or f[l1][l2-2] || ((p[l2-2]=='.' || p[l2-2] == s[l1-1]) && f[l1-1][l2])
    //f[0][0] = true, f[x][0] = false if x >= 1. f[0][x] when x >= 1 needs to be computed
    public boolean isMatch(String s, String p) {
        if (s == null || p == null) {
            return false;
        }
        int ls = s.length();
        int lp = p.length();
        boolean[] dp = new boolean[lp + 1];
        for (int l1 = 0; l1 <= ls; ++l1) {
            boolean prev = dp[0];
            dp[0] = (l1 == 0);
            for (int l2 = 1; l2 <= lp; ++l2) {
                boolean temp = dp[l2];
                char pc = p.charAt(l2 - 1);
                if (pc != '*') {
                    if (l1 == 0) {
                        dp[l2] = false;
                    } else {
                        dp[l2] = (pc == '.' || pc == s.charAt(l1 - 1)) && prev;
                    }
                } else {
                    if (l1 > 0) {
                        char prevPc = p.charAt(l2 - 2);
                        dp[l2] = (prevPc == '.' || prevPc == s.charAt(l1 - 1))
                                && dp[l2];
                    }
                    dp[l2] |= dp[l2 - 2];
                }
                prev = temp;
            }
        }
        return dp[lp];
    }

    public static void main(String[] args) {
    	String s = "aa";
    	String p = "aa";
    	System.out.println("s = " + s);
    	System.out.println("p = " + p);
    	RegularExpressionMatching rem = new RegularExpressionMatching();
    	System.out.println(rem.isMatch(s, p));
    }
}