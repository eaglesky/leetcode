public class SubStringWithConcatAllWords {
    
    // O(words_num + word_length * s_length) time
    // O(words_num * word_length) space
    private static void findSubstring(String s, int start, Map<String, Integer> wordCounts, int wordLen, int nWords, List<Integer> result) {
        if (wordLen * wordCounts.size() > s.length() - start) {
            return;
        }
        Map<String, Integer> seenWordCounts = new HashMap<>();
        int windowStart = start;
        int i = start;
        for (; i <= s.length() - wordLen; i += wordLen) {
            String curWord = s.substring(i, i+wordLen);
            int curCount = seenWordCounts.getOrDefault(curWord, 0);
            Integer expectedCountInt = wordCounts.get(curWord);
            if (expectedCountInt != null) {
                seenWordCounts.put(curWord, curCount + 1);
            }
            if ((expectedCountInt == null) || (curCount == expectedCountInt.intValue())) {
                if (expectedCountInt == null) {
                    windowStart = i + wordLen;
                    seenWordCounts.clear();
                } else {
                    for(; windowStart < i; windowStart += wordLen) {
                        String seenWord = s.substring(windowStart, windowStart+wordLen);
                        seenWordCounts.put(seenWord, seenWordCounts.get(seenWord)-1);
                        if (seenWord.equals(curWord)) {
                            windowStart += wordLen;
                            break;
                        }
                    }
                }
            } 
            if ((i - windowStart) / wordLen + 1 == nWords) {
                result.add(windowStart);
            }
        }
    }
    
    public List<Integer> findSubstring(String s, String[] words) {
        List<Integer> result = new ArrayList<>();
        if (words.length == 0) {
            return result;
        }
        Map<String, Integer> wordCounts = new HashMap<>();
        for(String word : words) {
            int count = wordCounts.getOrDefault(word, 0);
            wordCounts.put(word, count + 1);
        }

        int wordLen = words[0].length();
        for (int i = 0; i < wordLen; ++i) {
            findSubstring(s, i, wordCounts, wordLen, words.length, result);
        }
        return result;
    }
}