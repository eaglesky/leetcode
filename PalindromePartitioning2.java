public class PalindromePartitioning2 {

    //DP solution, O(n^2) time and O(n) space
    //mins[i] is the min cut number for s[i...]. 
    //isPalindrome[i][j] is true when s[i...j] is a palindrome.
    //For each s[i...](from right to left), if s[i...j] is a palindrome, then
    //there could be a cut after s[j], whose cut number is mins[j+1] + 1. 
    //Do this for each index after i and get the minimum.
    //The 2D array isPalindrome can be reduced to 1D like below,
    //which requires the inner loop starts from right to left.
    public int minCut0(String s) {
        int len = s.length();
        if (len == 0) {
            return 0;
        }
        boolean[] isPalindrome = new boolean[len];
        int[] mins = new int[len];
        for (int i = len - 1; i >= 0; --i) {
            mins[i] = Integer.MAX_VALUE;
            for (int j = len - 1; j >= i; --j) {
                isPalindrome[j] = (i == j) || (s.charAt(i) == s.charAt(j) && (i == j-1 || isPalindrome[j-1]));
                if (isPalindrome[j]) {
                    mins[i] = (j == len - 1) ? 0 : Math.min(mins[i], mins[j+1] + 1);
                }
            }               
        }
        return mins[0];
    }
    
    //Another DP solution. O(n^2) time and O(n) space.
    //Faster than above soltuion.
    //mins[i] is the min cut number of s[0...i-1].
    //Iterate the string from left to right
    //For each s[i], scan the substrings that centers at it
    //from the shortest to the longest, and stops when the substring
    //is no longer a palindrome. Need to scan two times, one for odd length,
    //the other for even length. For each palindrome substring s[i-j...i+j] or 
    //s[i-1-j, i+j], update mins[i+j+1] to be mins[i-j] + 1 or mins[i-1-j] + 1,
    //if they are smaller than the previous value.
    public int minCut(String s) {
        int len = s.length();
        int[] mins = new int[len+1];
        for (int i = 0; i <= len; ++i) {
            mins[i] = i - 1;
        }
        for (int i = 1; i < len; ++i) {
            for (int j = 0; i-j >= 0 && i+j < len && s.charAt(i-j) == s.charAt(i+j); ++j) {
                mins[i+j+1] = Math.min(mins[i+j+1], mins[i-j] + 1);
            }
            for (int j = 0; i-1-j >= 0 && i+j < len && s.charAt(i-1-j) == s.charAt(i+j); ++j) {
                mins[i+j+1] = Math.min(mins[i+j+1], mins[i-1-j] + 1);
            }
        }
        return mins[len];
    }
}