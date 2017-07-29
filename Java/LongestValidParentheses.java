public class LongestValidParentheses {
    /* The longest valid parentheses is always bounded by breaking parentheses,
    * which could be either unmatched '(' or unmatched ')'.
    * Stack solution can takes care of this easily using one pass since it stores
    * any breaking parentheses.
    * However counter solution needs two passes. 
    * Both solutions need to update maxLen whenever a match is found.
    */


    //Stack solution. O(n) time and O(n) space
    public int longestValidParentheses0(String s) {
        Deque<Integer> stack = new ArrayDeque<>(s.length());
        int maxLen = 0;
        for (int i = 0; i < s.length(); ++i) {
            if (s.charAt(i) == '(') {
                stack.push(i);
            } else {
                Integer id = stack.peek();
                if (id == null || s.charAt(id) == ')') {
                    stack.push(i);
                } else {
                    stack.pop();
                    Integer nextId = stack.peek();
                    int curLen = (nextId == null) ? i + 1 : i - nextId;
                    maxLen = Math.max(maxLen, curLen);
                }
            }
        }
        return maxLen;
    }

    //Two passes solution. O(n) time and O(1) space
    public int longestValidParentheses(String s) {
        int leftParenCounter = 0, rightParenCounter = 0;
        int maxLen = 0;
        for (int i = 0; i < s.length(); ++i) {
            if (s.charAt(i) == '(') {
                leftParenCounter++;
            } else {
                rightParenCounter++;
            }
            if (leftParenCounter == rightParenCounter) {
                maxLen = Math.max(maxLen, leftParenCounter + rightParenCounter);
            } else if (leftParenCounter < rightParenCounter) {
                leftParenCounter = 0;
                rightParenCounter = 0;
            }
        }
        leftParenCounter = 0;
        rightParenCounter = 0;
        for (int i = s.length() - 1; i >= 0; --i) {
            if (s.charAt(i) == '(') {
                leftParenCounter++;
            } else {
                rightParenCounter++;
            }
            if (leftParenCounter == rightParenCounter) {
                maxLen = Math.max(maxLen, leftParenCounter + rightParenCounter);
            } else if (leftParenCounter > rightParenCounter) {
                leftParenCounter = 0;
                rightParenCounter = 0;
            }
        }
        return maxLen;
    }
    
    //Improved version of above:
    private int scan(String s, char checkedParen) {
        int maxLen = 0;
        int inc = checkedParen == ')' ? 1 : -1;
        int startId = checkedParen == ')' ? 0 : s.length() - 1;
        int prevId = checkedParen == ')' ? -1 : s.length();
        int parenCount = 0;
        for (int i = startId; i >= 0 && i < s.length(); i += inc) {
            char c = s.charAt(i);
            if (c == checkedParen) {
                if (parenCount > 0) {
                    parenCount--;
                    if (parenCount == 0) {
                        maxLen = Math.max(maxLen, Math.abs(i - prevId));
                    }
                } else {
                    prevId = i;
                }
            } else {
                parenCount++;
            }
        }
        return maxLen;
    }
    
    public int longestValidParentheses(String s) {
        if (s == null || s.isEmpty()) {
            return 0;
        }
        int maxLen1 = scan(s, ')');
        int maxLen2 = scan(s, '(');
        return Math.max(maxLen1, maxLen2);
    }


    //See c++ code for the DP solution, not recommended since it is no better than
    //the stack solution.
}