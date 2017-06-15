public class Solution {
    public int shortestWordDistance(String[] words, String word1, String word2) {
        int id1 = -1;
        int id2 = -1;
        int dis = words.length;
        boolean areSame = word1.equals(word2);
        for (int i = 0; i < words.length; ++i) {
            if (words[i].equals(word1)) {
                if (id2 >= 0) {
                    dis = Math.min(dis, i - id2);
                }
                id1 = i;
                if (areSame) {
                    id2 = i;
                }
            } else if (words[i].equals(word2)) {
                if (id1 >= 0) {
                    dis = Math.min(dis, i - id1);
                }
                id2 = i;
            }
        }
        return dis;
    }
}