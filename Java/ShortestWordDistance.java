public class Solution {
    
    //O(n) time and O(1) space. Best in performance
    public int shortestDistance(String[] words, String word1, String word2) {
        int lastId1 = -1;
        int lastId2 = -1;
        int dis = words.length;
        for (int i = 0; i < words.length; ++i) {
            if (words[i].equals(word1)) {
                if (lastId2 >= 0) {
                    dis = Math.min(dis, i - lastId2);
                }
                lastId1 = i;
            } else if (words[i].equals(word2)) {
                if (lastId1 >= 0) {
                    dis = Math.min(dis, i - lastId1);
                }
                lastId2 = i;
            }
        }
        return dis;
    }
    
    //Another implementation
    public int shortestDistance0(String[] words, String word1, String word2) {
        int lastId1 = -1;
        int lastId2 = -1;
        int dis = words.length;
        for (int i = 0; i < words.length; ++i) {
            if (words[i].equals(word1)) {
                lastId1 = i;
            } else if (words[i].equals(word2)) {
                lastId2 = i;
            }
            if (lastId1 >= 0 && lastId2 >= 0) {
                dis = Math.min(dis, Math.abs(lastId1 - lastId2));
            }
        }
        return dis;
    }
}