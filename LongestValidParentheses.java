public class LongestValidParentheses {
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
    
    //See c++ code for the DP solution
}