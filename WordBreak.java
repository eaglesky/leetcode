public class WordBreak {
    
    //DP solution, O(n^2) time and O(n) space
    //flags[l] is true when s[0...l-1] is breakable,
    //i.e.there exists j (0 <= j < l) so that flags[j] = true
    // && s[j...l-1] exists in wordSet.
    public boolean wordBreak(String s, List<String> wordDict) {
        Set<String> wordSet = new HashSet<>();
        for (String word : wordDict) {
            wordSet.add(word);
        }
        boolean[] flags = new boolean[s.length() + 1];
        flags[0] = true;
        for (int i = 0; i < s.length(); ++i) {
            for (int j = 0; j <= i; ++j) {
                String str = s.substring(j, i+1);
                if (flags[j] && wordSet.contains(str)) {
                    flags[i + 1] = true;
                    break;
                }
            }
        }
        return flags[s.length()];
    }

    //Can also be solved using BFS and DFS. See the c++ solution.
}