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

    public static void main(String[] args) {
    	String s = "aa";
    	String p = "aa";
    	System.out.println("s = " + s);
    	System.out.println("p = " + p);
    	RegularExpressionMatching rem = new RegularExpressionMatching();
    	System.out.println(rem.isMatch(s, p));
    }
}