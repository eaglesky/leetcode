public class WordBreak2 {
    
    private void dfs(String s, int start, Set<String> wordSet, List<List<String>> strs) {
        if (strs.get(start) != null) {
            return;
        }
        if (start >= s.length()) {
            strs.set(start, new ArrayList<String>());
            strs.get(start).add("");
            return;
        }
        strs.set(start, new ArrayList<String>());
        for (int i = start; i < s.length(); ++i) {
            String curWord = s.substring(start, i + 1);
            if (wordSet.contains(curWord)) {
                dfs(s, i+1, wordSet, strs);
                if (strs.get(i+1) != null) {
                    for (String str : strs.get(i+1)) {
                        if (str.isEmpty()) {
                            strs.get(start).add(curWord);
                        } else {
                            strs.get(start).add(curWord + " " + str);                    
                        }
                    }                
                }
            }
        }
    }
    
    //Memoization solution. 
    public List<String> wordBreak(String s, List<String> wordDict) {
        Set<String> wordSet = new HashSet<>();
        for (String word : wordDict) {
            wordSet.add(word);
        }
        List<List<String>> strs = new ArrayList<>(Collections.nCopies(s.length()+1, null));
        dfs(s, 0, wordSet, strs);
        return strs.get(0);
    }

    //See C++ solution for the DP approach
}