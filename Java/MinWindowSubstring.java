public class MinWindowSubstring {

    // O(ns + nt) time and O(nt) space
    public String minWindow0(String s, String t) {
        if (t.length() > s.length() || t.isEmpty()) {
            return "";
        }
        Map<Character, Integer> TcharCounts = new HashMap<>();
        for (int i = 0; i < t.length(); ++i) {
            Integer curCountInt = TcharCounts.get(t.charAt(i));
            int curCount = (curCountInt == null) ? 0 : curCountInt;
            TcharCounts.put(t.charAt(i), curCount + 1);
        }

        int minLen = s.length() + 1;
        int minStart = 0;
        Map<Character, Integer> ScharCounts = new HashMap<>();
        int start = 0;
        int nSValues = 0;
        for (int i = 0; i < s.length(); ++i) {
            Integer TcharCountInt = TcharCounts.get(s.charAt(i));
            if (TcharCountInt != null) {
                if (nSValues < t.length()) {
                    Integer ScharCountInt = ScharCounts.get(s.charAt(i));
                    int curCount = (ScharCountInt == null) ? 0 : ScharCountInt;
                    ScharCounts.put(s.charAt(i), curCount + 1);
                    if (curCount < TcharCountInt.intValue()) {
                        nSValues++;
                    }
                }
                if (nSValues == t.length()) {
                    for (; start <= i; ++start) {
                        if (TcharCounts.containsKey(s.charAt(start))) {
                            if (i - start + 1 < minLen) {
                                minLen = i - start + 1;
                                minStart = start;
                            }
                            int curCount = ScharCounts.get(s.charAt(start));
                            ScharCounts.put(s.charAt(start), --curCount);
                            if (curCount < TcharCounts.get(s.charAt(start))) {
                                nSValues--;
                                start++;
                                break;
                            }
                        }
                    }
                }
                if (minLen == t.length()) {
                    break;
                }
            }
        }
        return (minLen <= s.length())
                ? s.substring(minStart, minStart + minLen) : "";
    }

    //Second try
    public String minWindow1(String s, String t) {
        if (s.length() < t.length() || t.isEmpty()) {
            return "";
        }
        Map<Character, Integer> charCountsT = new HashMap<>();
        for (int i = 0; i < t.length(); ++i) {
            char c = t.charAt(i);
            int count = charCountsT.getOrDefault(c, 0);
            charCountsT.put(c, count + 1);
        }
        Map<Character, Integer> charCountsS = new HashMap<>();
        int numIncluded = 0;
        int start = 0;
        int minLen = s.length() + 1;
        int minStart = 0;
        for (int i = 0; i < s.length(); ++i) {
            char c = s.charAt(i);
            if (charCountsT.containsKey(c)) {
                int count = charCountsS.getOrDefault(c, 0);
                charCountsS.put(c, ++count);     
                if (count == charCountsT.get(c)) {
                    numIncluded++;
                }
            } else {
                continue;
            }
            if (numIncluded == charCountsT.size()) {
                for (; start <= i && numIncluded == charCountsT.size(); ++start) {
                    char pre = s.charAt(start); 
                    int preCountInT = charCountsT.getOrDefault(pre, 0);
                    if (preCountInT == 0) {
                        continue;
                    }
                    minStart = (minLen > i - start + 1) ? start : minStart;
                    minLen = Math.min(minLen, i - start + 1);
                    int preCount = charCountsS.get(pre);
                    if (preCount == 1) {
                        charCountsS.remove(pre);
                    } else {
                        charCountsS.put(pre, preCount - 1);
                    }
                    if (preCount - 1 < preCountInT) {
                        numIncluded--;
                    }                    
                }
            }            
        }
        return minLen > s.length() ? "" : s.substring(minStart, minStart + minLen);
    }

    //Third try. Using only one hash map now.
    //Using array instead of charCountsT can improve the running time.
    public String minWindow(String s, String t) {
        if (s.length() < t.length() || t.isEmpty()) {
            return "";
        }
        //This map only stores characters in t. Later logic
        //won't populate it with other characters. This is because
        //this map needs to be used for checking if s char is in
        //t or not.
        Map<Character, Integer> charCountsT = new HashMap<>();
        for (int i = 0; i < t.length(); ++i) {
            char c = t.charAt(i);
            int count = charCountsT.getOrDefault(c, 0);
            charCountsT.put(c, count + 1);
        }
        int start = 0;
        int minLen = s.length() + 1;
        int minStart = 0;
        int nUniqueLeft = charCountsT.size();
        for (int i = 0; i < s.length(); ++i) {
            char c = s.charAt(i);
            Integer countInt = charCountsT.get(c);
            //First step, check if s char is in t or not.
            //If it is, just decrease the count directly and
            //check if left pointer needs to be moved later.
            //This can make the logic of incrementing i much
            //easier. 
            //If it is not, for this problem we just need to
            //move the right pointer.
            if (countInt != null) {
                int count = countInt - 1;
                charCountsT.put(c, count);
                if (count == 0) {
                    nUniqueLeft--;
                }
                for (; start <= i && nUniqueLeft == 0; ++start) {
                    int curLen = i - start + 1;
                    if (curLen < minLen) {
                        minLen = curLen;
                        minStart = start;
                    }
                    char pre = s.charAt(start);
                    Integer preCountInt = charCountsT.get(pre);
                    if (preCountInt != null) {
                        int preCount = preCountInt + 1;
                        if (preCount > 0) {
                            nUniqueLeft++;
                        }
                        charCountsT.put(pre, preCount);
                    }
                }
            }
        }
        return minLen > s.length() ? "" : s.substring(minStart, minStart + minLen);
    }
}