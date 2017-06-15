public class WordDistance {
    private static class Pair {
        final String w1;
        final String w2;
        Pair(String w1, String w2) {
            this.w1 = w1;
            this.w2 = w2;
        }
        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof Pair)) {
                return false;
            }
            Pair o = (Pair) other;
            return (this.w1.equals(o.w1) && this.w2.equals(o.w2))
                || (this.w1.equals(o.w2) && this.w2.equals(o.w1));
        }
        public int hashCode() {
            return w1.hashCode() + w2.hashCode();
        }
    }
    
    private final Map<Pair, Integer> pairDis = new HashMap<>();
    
    //O(n^2) pre-processing time and O(1) get time. 
    //TLE. Should be accepted.
    public void WordDistance0(String[] words) {
        for (int i = 0; i < words.length; ++i) {
            for (int j = i + 1; j < words.length; ++j) {
                Pair pair = new Pair(words[i], words[j]);
                Integer dis = pairDis.get(pair);
                if (dis == null) {
                    pairDis.put(pair, j - i);
                } else {
                    pairDis.put(pair, Math.min(dis, j - i));
                }
            }
        }
    }
    
    public int shortest0(String word1, String word2) {
        return pairDis.get(new Pair(word1, word2));
    }
    
    
    //O(n) pre-processing time and O(n) get time
    //Could cache the word pair and their distance to further optimize
    //https://discuss.leetcode.com/topic/20643/java-solution-using-hashmap/14
    private final Map<String, List<Integer>> wordToIndices = new HashMap<>();
    
    public WordDistance(String[] words) {
        for (int i = 0; i < words.length; ++i) {
            wordToIndices
                .computeIfAbsent(words[i], k -> new ArrayList<>())
                .add(i);
        }
    }
    
    public int shortest(String word1, String word2) {
        List<Integer> ids1 = wordToIndices.get(word1);
        List<Integer> ids2 = wordToIndices.get(word2);
        if (ids1 == null || ids2 == null) {
            return -1;
        }
        int dis = Integer.MAX_VALUE;
        for (int i1 = 0, i2 = 0; i1 < ids1.size() && i2 < ids2.size();) {
            int id1 = ids1.get(i1);
            int id2 = ids2.get(i2);
            if (id1 < id2) {
                dis = Math.min(dis, id2 - id1);
                i1++;
            } else {
                dis = Math.min(dis, id1 - id2);
                i2++;
            }
        }
        return dis;
    }
}

/**
 * Your WordDistance object will be instantiated and called as such:
 * WordDistance obj = new WordDistance(words);
 * int param_1 = obj.shortest(word1,word2);
 */