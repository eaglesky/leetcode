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

    //Another naive solution using one loop
    public int lengthOfLongestSubstring1(String s) {
        Set<Character> chars = new HashSet<>();
        int start = 0;
        int maxLen = 0;
        for (int i = 0; i < s.length();) {
            char c = s.charAt(i);
            if (chars.contains(c)) {
                chars.remove(s.charAt(start));
                start++;
            } else {
                chars.add(c);
                maxLen = Math.max(maxLen, i - start + 1);
                ++i;
            }
        }
        return maxLen;
    }
    
    //Modified from above:
    public int lengthOfLongestSubstring(String s) {
        Map<Character, Integer> charPos = new HashMap<>();
        int start = 0;
        int maxLen = 0;
        for (int i = 0; i < s.length();) {
            char c = s.charAt(i);
            int prevPos = charPos.getOrDefault(c, -1);
            if (prevPos >= start) {
                start = prevPos + 1;
            } else {
                charPos.put(c, i);
                maxLen = Math.max(maxLen, i - start + 1);
                ++i;
            }
        }
        return maxLen;
    }

    // Another similar one
    // All of the above solutions are O(n) time and O(n) space
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

    // This might be easier to come up with in interview
    public int lengthOfLongestSubstring(String s) {
        int maxLen = 0;
        if (s == null || s.length() == 0) {
            return maxLen;
        }
        Set<Character> visited = new HashSet<>();
        int prev = 0;
        for (int i = 0; i < s.length();) {
            char c = s.charAt(i);
            for(; visited.contains(c); ++prev) {
                visited.remove(s.charAt(prev));
            }
            visited.add(c);
            maxLen = Math.max(maxLen, i - prev + 1);
            ++i;
        }
        return maxLen;
    }
}