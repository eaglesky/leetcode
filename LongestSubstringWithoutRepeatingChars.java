public class LongestSubstringWithoutRepeatingChars {

    // First try
    public int lengthOfLongestSubstring0(String s) {
        int maxSubStrLen = 0;
        Map<Character, Integer> charToPos = new HashMap<>();
        if (s.isEmpty()) {
            return 0;
        }
        for (int i = 0, j = 0; i < s.length();) {
            for (;(j < s.length()) && (!charToPos.containsKey(s.charAt(j))); ++j) {
                charToPos.put(s.charAt(j), j);
            }
            int curLen = j - i;
            maxSubStrLen = Math.max(maxSubStrLen, curLen);
            if (j == s.length()) {
                break;
            }
            int prevDupId = charToPos.get(s.charAt(j));
            for(; i <= prevDupId; ++i) {
                charToPos.remove(s.charAt(i));
            }
        }
        return maxSubStrLen;
    }
    
    // Improved vervsion
    public int lengthOfLongestSubstring(String s) {
        int maxLen = 0;
        Map<Character, Integer> charToPos = new HashMap<>();
        int start = 0;
        int i = 0;
        for (; i < s.length(); ++i) {
            Integer prevIdInteger = charToPos.get(s.charAt(i));
            int prevId = (prevIdInteger == null) ? -1 : prevIdInteger;
            if (prevId >= start) {
                maxLen = Math.max(maxLen, i - start);
                start = prevId + 1;
            }
            charToPos.put(s.charAt(i), i);
        }
        maxLen = Math.max(maxLen, i - start);
        return maxLen;
    }
}