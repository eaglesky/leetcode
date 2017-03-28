import java.util.*;

public class RemoveInvalidParentheses {
    
    //https://discuss.leetcode.com/topic/34875/easy-short-concise-and-fast-java-dfs-3-ms-solution/2
    //O(nk) time, k is the number of recursive calls.
    //This is essentially top-down approach -- pass the whole string with some letters removed
    //to the new state.
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
    
    public List<String> removeInvalidParentheses0(String s) {
        List<String> result = new ArrayList<>();
        removeRecur(result, s, 0, 0, new char[]{'(', ')'});
        return result;
    }

   	//Another top-down approach using BFS:
   	//https://discuss.leetcode.com/topic/28827/share-my-java-bfs-solution
   	//A bit too brute-force.. memory overhead is also too large

    //Bottom-up DFS solution.
    private static void dfs(StringBuilder sb, String s, int id, int rLeftParen, int rRightParen,
            Set<String> visited, List<String> result) {
        if (rLeftParen > rRightParen || rLeftParen < 0 || rRightParen < 0) {
            return;
        }
        if (id >= s.length()) {
            if (rLeftParen == 0 && rRightParen == 0) {
                String curStr = sb.toString();
                if (!visited.contains(curStr)) {
                    result.add(curStr);
                    visited.add(curStr);
                }
            }
        	return;
        }
        int len = sb.length();
        char c = s.charAt(id);
        if (c == '(') {
            dfs(sb, s, id + 1, rLeftParen, rRightParen, visited, result);
            dfs(sb.append(c), s, id + 1, rLeftParen-1, rRightParen, visited, result);
        } else if (c == ')') {
            dfs(sb, s, id + 1, rLeftParen, rRightParen, visited, result);
            dfs(sb.append(c), s, id + 1, rLeftParen, rRightParen - 1, visited, result);
        } else {
            dfs(sb.append(c), s, id + 1, rLeftParen, rRightParen, visited, result);
        }
        sb.setLength(len);
    }
    
    public List<String> removeInvalidParentheses(String s) {
        int numParen = 0;
        int numLeftParen = 0;
        for (int i = 0; i < s.length(); ++i) {
            if (s.charAt(i) == '(') {
                numLeftParen++;
            } else if (s.charAt(i) == ')') {
                if (numLeftParen > 0) {
                    numLeftParen--;
                    numParen++;
                }
            }
        }
        List<String> result = new ArrayList<>();
        dfs(new StringBuilder(), s, 0, numParen, numParen, new HashSet<String>(), result);
        return result;
    }

    public static void main(String[] args) {
    	String[] tests = new String[] {
    		"()())()",
			"(a)())()",
			")(",
			")",
			"(",
			"((()()(",
			"abc"
    	};
    	RemoveInvalidParentheses solution = new RemoveInvalidParentheses();
    	for (String test : tests) {
    		List<String> result = solution.removeInvalidParentheses(test);
    		System.out.println(test + ": ");
    		System.out.println(result);
    		System.out.println("");
    	}
    }
}