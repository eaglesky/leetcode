class PermutationInString {
    
    //If assuming the character set is small, we can use this solution:
    //https://discuss.leetcode.com/topic/87845/java-solution-sliding-window
    //Easier to code than the other solution!
    
    //Standard sliding window solution, guranteed O(l1 + l2) time and O(l1) space
    public boolean checkInclusion(String s1, String s2) {
        if (s1 == null || s2 == null || s1.length() > s2.length()) {
            return false;
        }
        Map<Character, Integer> charCounts = new HashMap<>();
        for (int i = 0; i < s1.length(); ++i) {
            char c = s1.charAt(i);
            int count = charCounts.getOrDefault(c, 0);
            charCounts.put(c, count + 1);
        }
        int prevId = 0;
        for (int i = 0; i < s2.length(); ++i) {
            char c = s2.charAt(i);
            Integer count = charCounts.get(c);
            if (count != null) {
                charCounts.put(c, --count);
            }
            if (count == null || count < 0) {
                for(; prevId <= i; ++prevId) {
                    char prevC = s2.charAt(prevId);
                    Integer prevCount = charCounts.get(prevC);
                    if (prevCount != null) {
                        charCounts.put(prevC, prevCount + 1);
                    }
                    //Pay attention to the end condition here!
                    if (prevC == c) {
                        prevId++;
                        break;
                    }
                }
            }
            if (i - prevId + 1 == s1.length()) {
                return true;
            }
        }
        return false;
    }
}