public class Solution {
    
    //https://discuss.leetcode.com/topic/34875/easy-short-concise-and-fast-java-dfs-3-ms-solution/2
    //O(nk) time, k is the number of recursive calls.
    private void removeRecur(List<String> result, String s, int start, int lastRemoved, char[] parens) {
        int i = start;
        for (int count = 0; i < s.length(); ++i) {
            char c = s.charAt(i);
            if (c == parens[0]) {
                count++;
            } else if (c == parens[1] && --count < 0) {
                break;
            }
        }
        if (i < s.length()) {
            for (int j = lastRemoved; j <= i; ++j) {
                char c = s.charAt(j);
                if (c == parens[1] && (j == lastRemoved || c != s.charAt(j-1))) {
                    removeRecur(result, s.substring(0, j) + s.substring(j+1), i, j,parens);
                }
            }
        } else {
            String reversed = new StringBuilder(s).reverse().toString();
            if (parens[0] == '(') {
                removeRecur(result, reversed, 0, 0, new char[]{')', '('});
            } else {
                result.add(reversed);
            }
        }
    }
    
    public List<String> removeInvalidParentheses(String s) {
        List<String> result = new ArrayList<>();
        removeRecur(result, s, 0, 0, new char[]{'(', ')'});
        return result;
    }
}