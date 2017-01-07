public class InterleavingString {
    
    private boolean isInterleaveRecursive(String s1, String s2, int l1, int l2, String s3, Boolean[][] cache) {
        if (cache[l1][l2] != null) {
            return cache[l1][l2];
        }
        if (l1 == 0 && l2 == 0) {
            cache[l1][l2] = true;
        } else if (l1 == 0) {
            cache[l1][l2] = (s2.charAt(l2-1) == s3.charAt(l2-1)) && isInterleaveRecursive(s1, s2, l1, l2-1, s3, cache);
        } else if (l2 == 0) {
            cache[l1][l2] = (s1.charAt(l1-1) == s3.charAt(l1-1)) && isInterleaveRecursive(s1, s2, l1-1, l2, s3, cache);
        } else {
            cache[l1][l2] = (((s1.charAt(l1-1) == s3.charAt(l1+l2-1)) && isInterleaveRecursive(s1, s2, l1-1, l2, s3, cache))
                      || ((s2.charAt(l2-1) == s3.charAt(l1+l2-1)) && isInterleaveRecursive(s1, s2, l1, l2-1, s3, cache)));
        }
        return cache[l1][l2];
    }
    
    //Memoization solution
    public boolean isInterleave0(String s1, String s2, String s3) {
        int len1 = s1.length();
        int len2 = s2.length();
        if (s3.length() != len1 + len2) {
            return false;
        }
        Boolean[][] cache = new Boolean[len1+1][len2+1];
        return isInterleaveRecursive(s1, s2, len1, len2, s3, cache);
    }
    
    //DP solution, O(mn) time and O(n) space
    //cache[l1][l2] is true when s1[0...l1-1] and s2[0...l2-1] can form s3[0...l1+l2-1]
    //Slower than memoization solution
    public boolean isInterleave(String s1, String s2, String s3) {
        int len1 = s1.length();
        int len2 = s2.length();
        if (s3.length() != len1 + len2) {
            return false;
        }
        boolean[] cache = new boolean[len2+1];
        for (int l2 = 0; l2 <= len2; ++l2) {
            cache[l2] = (l2 == 0)
                     || ((s2.charAt(l2-1) == s3.charAt(l2-1)) && cache[l2-1]);
        }
        for (int l1 = 1; l1 <= len1; ++l1) {
            cache[0] = (s1.charAt(l1-1) == s3.charAt(l1-1)) && cache[0];
            for (int l2 = 1; l2 <= len2; ++l2) {
                cache[l2] = ((s1.charAt(l1-1) == s3.charAt(l1+l2-1) && cache[l2])
                          || (s2.charAt(l2-1) == s3.charAt(l1+l2-1) && cache[l2-1]));
            }
        }
        return cache[len2];
    }
}