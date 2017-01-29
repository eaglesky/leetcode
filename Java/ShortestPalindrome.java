public class ShortestPalindrome {

    //Better O(n^2) time and O(1) space solution that got accepted
    //Brute force.
    public String shortestPalindrome(String s) {
        int maxRightId = 0;
        for (int rightId = s.length() - 1; rightId >= 0; rightId--) {
            int start = 0;
            int end = rightId;
            for (; start < end && s.charAt(start) == s.charAt(end); start++, end--);
            if (start >= end) {
                maxRightId = rightId;
                break;
            }
        }
        StringBuilder sb = new StringBuilder();
        for (int i = s.length() - 1; i > maxRightId; --i) {
            sb.append(s.charAt(i));
        }
        sb.append(s);
        return sb.toString();
    }
    
    //KMP solution.
    //The original problem can be converted into finding the longest palindrome starting
    //with s[0]. And if we append s with its reverse(s_r) and add '#'(which does not appear
    //in s), we get this: s' = s'#'s_r, the problem can be further converted into finding
    //the longest prefix of s' that is equal to the suffix of s'.
    //This can be solved by KMP pre-processing algorithm that runs O(n) time and O(n) space.
    //https://discuss.leetcode.com/topic/21713/a-kmp-based-java-solution-with-explanation
    
}