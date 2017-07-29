public class ValidParentheses {

	//O(n) time and O(n) space
	//Cannot easiy solved by using counters!
    //The stack stores the unmatched left parentheses.
    //The incoming right parenthesis must match the one
    //on top of the stack, not any of those below.
    public boolean isValid(String s) {
        Deque<Character> stack = new ArrayDeque<>();
        for (int i = 0; i < s.length(); ++i) {
            char c = s.charAt(i);
            if (c == '(' || c == '{' || c == '[') {
                stack.push(c);
            } else {
                if (stack.isEmpty()
                || (c == ')' && stack.pop() != '(')
                || (c == '}' && stack.pop() != '{')
                || (c == ']' && stack.pop() != '[')) {
                    return false;
                }
            }
        }
        return stack.isEmpty();
    }
}