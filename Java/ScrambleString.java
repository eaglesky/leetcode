public class ScrambleString {
    
    //DP solution, O(n^4) time and O(n^3) space
    //b[l][i1][i2] is true if s1[i1...i1+l-1] is a scrambled string of s2[i2...i2+l-1]
    public boolean isScramble0(String s1, String s2) {
        int len = s1.length();
        if (s2.length() != len) {
            return false;
        } else if (len == 0) {
            return true;
        }
        boolean[][][] b = new boolean[len + 1][len][len];
        for (int i = 0; i < len; ++i) {
            for (int j = 0; j < len; ++j) {
                b[1][i][j] = s1.charAt(i) == s2.charAt(j);
            }
        }
        for (int l = 2; l <= len; ++l) {
            for (int i1 = 0; i1 <= len - l; ++i1) {
                for (int i2 = 0; i2 <= len - l; ++i2) {
                    b[l][i1][i2] = false;
                    for (int lx = 1; lx < l; ++lx) {
                        if ((b[lx][i1][i2] && b[l-lx][i1+lx][i2+lx]
                         || (b[lx][i1][i2+l-lx] && b[l-lx][i1+lx][i2]))) {
                             b[l][i1][i2] = true;
                             break;
                         }
                    }
                }
            }
        }
        return b[len][0][0];
    }
    
    private boolean fastNo(String s1, String s2, int i1, int i2, int len) {
        Map<Character, Integer> counts = new HashMap<>();
        for (int i = i1; i < i1+len; ++i) {
            int preCount = counts.getOrDefault(s1.charAt(i), 0);
            counts.put(s1.charAt(i), ++preCount);
        }
        for (int i = i2; i < i2+len; ++i) {
            int preCount = counts.getOrDefault(s2.charAt(i), 0);
            if (preCount == 0) {
                return false;
            }
            counts.put(s2.charAt(i), --preCount);                
        }
        return true;
    }
    
    private boolean isScrambleRecursive(String s1, String s2, int i1, int i2, int len, Boolean[][][] cache) {
        if (cache[len][i1][i2] != null) {
            return cache[len][i1][i2];
        }
        if (len == 1) {
            cache[len][i1][i2] = s1.charAt(i1) == s2.charAt(i2);
        } else {
            cache[len][i1][i2] = false;
            if (!fastNo(s1, s2, i1, i2, len)) {
                return false;
            }
            for (int lx = 1; lx < len; ++lx) {
                if ((isScrambleRecursive(s1, s2, i1, i2, lx, cache) 
                    && isScrambleRecursive(s1, s2, i1+lx, i2+lx, len-lx, cache))
                 || (isScrambleRecursive(s1, s2, i1, i2+len-lx, lx, cache)
                    && isScrambleRecursive(s1, s2, i1+lx, i2, len-lx, cache))) {
                        cache[len][i1][i2] = true;
                        break;
                    }
            }
        }
        return cache[len][i1][i2];
    }
    
    //Faster solution using Memoization
    public boolean isScramble(String s1, String s2) {
        int len = s1.length();
        if (s2.length() != len) {
            return false;
        } else if (len == 0) {
            return true;
        }
        Boolean[][][] cache = new Boolean[len + 1][len][len];
        return isScrambleRecursive(s1, s2, 0, 0, len, cache);
    }
}