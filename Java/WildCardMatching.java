public class WildCardMatching {
    
    //DP solution, O(ls*lp) time and O(ls) space
    public boolean isMatch0(String s, String p) {
        boolean[] pre = new boolean[s.length() + 1];
        boolean[] cur = new boolean[s.length() + 1];
        pre[0] = true;
        for (int lp = 1; lp <= p.length(); ++lp) {
            for (int ls = 0; ls <= s.length(); ++ls) {
                if (p.charAt(lp-1) == '?') {
                    cur[ls] = (ls > 0) && pre[ls - 1];
                } else if (p.charAt(lp-1) == '*') {
                    cur[ls] = pre[ls] || (ls > 0 && cur[ls-1]);
                } else {
                    cur[ls] = (ls > 0) && p.charAt(lp-1) == s.charAt(ls-1) && pre[ls-1];
                }
            }
            boolean[] temp = pre;
            pre = cur;
            cur = temp;
        }
        return pre[s.length()];
    }
    
    //Backtracking solution(greedy), O(ls*lp) time and O(1) space
    //See the c++ solution for the explanation
    //p = "p1*p2*p3", p can be seen as consisting of several substrings separated
    //by '*'. We can try finding the first substring in s that matchs pi. When
    //it comes to pi, p1 .. pi-1 have been matched and is is the index after
    //pi is matched. if there is no substring in s(is...) matching pi, then
    //pi cannot be matched in s(...is), so should return false. 
    public boolean isMatch(String s, String p) {
        int is = 0, ip = 0;
        int preS = 0;
        int preStar = -1;
        for (; is < s.length();) {
            if ((ip < p.length()) && (p.charAt(ip) == '?' || (p.charAt(ip) != '*' && s.charAt(is) == p.charAt(ip)))) {
                is++;
                ip++;
            } else if (ip < p.length() && p.charAt(ip) == '*') {
                preStar = ip++;
                preS = is;
            } else if (preStar >= 0) {
                ip = preStar + 1;
                is = ++preS;
            } else {
                return false;
            }
        }
        for (; ip < p.length() && p.charAt(ip) == '*'; ++ip);
        return (ip == p.length());
    }
}