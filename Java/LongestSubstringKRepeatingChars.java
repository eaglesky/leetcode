public class LongestSubstringKRepeatingChars {
    
    //Two pointers. O(N*n) time and O(N) space.
    //N is the number of total possible characters, 
    //n is the length of the string.
    //The idea is to find out the longest substring that contains
    //exactly nUnique characters, where nUnique ranges from 1 to N.
    public int longestSubstring0(String s, int k) {
        if (s.length() < k) {
            return 0;
        }
        int N = 26;
        int maxLen = 0;
        for (int nUnique = 1; nUnique <= N; ++nUnique) {
            int start = 0;
            int nNoLessThanK = 0;
            Map<Character, Integer> counts = new HashMap<>();
            for (int i = 0; i < s.length();) {
                char c = s.charAt(i);
                int count = counts.getOrDefault(c, 0);
                if (counts.size() < nUnique
                || (counts.size() == nUnique && count > 0)) {
                    counts.put(c, ++count);
                    if (count == k) {
                        nNoLessThanK++;
                    }
                    ++i;
                } else {
                    char pre = s.charAt(start);
                    int preCount = counts.getOrDefault(pre, 0);
                    if (preCount == 1) {
                        counts.remove(pre);
                    } else {
                        counts.put(pre, preCount - 1);
                    }
                    if (preCount == k) {
                        nNoLessThanK--;
                    }
                    start++;
                }
                if (counts.size() == nUnique && nNoLessThanK == nUnique) {
                    maxLen = Math.max(maxLen, i - start);
                }
            }
        }
        return maxLen;
    }

    //Recursive solution. Should be faster than O(n^2)
    //O(n^2) space?
    private int longestSubstringRec(String s, int k, int start, int end) {
        if (end - start + 1 < k) {
            return 0;
        }
        Map<Character, Integer> counts = new HashMap<>();
        int nNoLessThanK = 0;
        for (int i = start; i <= end; ++i) {
            char c = s.charAt(i);
            int count = counts.getOrDefault(c, 0);
            counts.put(c, ++count);
            if (count == k) {
                nNoLessThanK++;
            }
        }
        if (nNoLessThanK == 0) {
            return 0;
        } else if (nNoLessThanK == counts.size()) {
            return end - start + 1;
        }
        int prevSepId = start - 1;
        int maxLen = 0;
        for (int i = start; i <= end; ++i) {
            char c = s.charAt(i);
            if (counts.get(c) < k) {
                maxLen = Math.max(maxLen, longestSubstringRec(s, k, prevSepId + 1, i - 1));
                prevSepId = i;
            }
        }
        return Math.max(maxLen, longestSubstringRec(s, k, prevSepId + 1, end));
    }
    
    public int longestSubstring(String s, int k) {
        return longestSubstringRec(s, k, 0, s.length() - 1);
    }
}