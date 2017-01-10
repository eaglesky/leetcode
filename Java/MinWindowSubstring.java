public class MinWindowSubstring {

    // O(ns + nt) time and O(nt) space
    public String minWindow(String s, String t) {
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
}