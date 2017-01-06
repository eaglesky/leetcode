public class EditDistance {
    
    //DP solution, O(mn) time and O(n) space. Two arrays can be reduced to 1 according to my C++ solution:
    //https://discuss.leetcode.com/topic/3136/my-o-mn-time-and-o-n-space-solution-using-dp-with-explanation
    
    //Each conversion operation should resolve at least one diff, which is either
    //diff in length or diff in character. To find out the minimum conversion sequence,
    //the minimum diffs should be found out first, and then starts from that.
    //Let d[l1][l2] be the minimum edit distance from word1[0...l1-1] to word2[0...l2-1].
    //d[l1][l2] = d[l1-1][l2-1] if word1[l1-1] = word2[l2-1]
    //       min (d_insert, d_replace, d_delete) if otherwise.
    //where d_insert is the edit distance after inserting word2[l2-1] to the end of word1, 
    //d_replace is the edit distance after replacing word1[l1-1] with word2[l2-1],
    //and d_delete is the edit distance after delete the last character of word1.
    //One of the three ways must be used in order to convert word1 to word2.
    //So if word1[l1-1] != word2[l2-1],
    //d[l1][l2] = min(d[l1][l2-1], d[l1-1][l2-1], d[l1-1][l2]) + 1
    public int minDistance(String word1, String word2) {
        int[] preDisArray = new int[word2.length() + 1];
        int[] curDisArray = new int[word2.length() + 1];
        for (int l1 = 0; l1 <= word1.length(); ++l1) {
            curDisArray[0] = l1;
            for (int l2 = 1; l2 <= word2.length(); ++l2) {
                if (l1 == 0) {
                    curDisArray[l2] = l2;
                } else {
                    if (word1.charAt(l1-1) == word2.charAt(l2-1)) {
                        curDisArray[l2] = preDisArray[l2-1];
                    } else {
                        curDisArray[l2] = Math.min(Math.min(curDisArray[l2-1], preDisArray[l2-1]), preDisArray[l2]) + 1;
                    }
                }
            }
            int[] temp = preDisArray;
            preDisArray = curDisArray;
            curDisArray = temp;
        }
        return preDisArray[word2.length()];
    }
}