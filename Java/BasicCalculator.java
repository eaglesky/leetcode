public class BasicCalculator {
    
    //First try. O(n) time and O(n) space
    //Might be extended to the case when there are operators like * and /.
    //Assuming there are no spaces between digits
    //Note that the prevSign of the first character after ( is always +.
    public int calculate(String s) {
        Deque<Integer> stack = new ArrayDeque<>();
        Deque<Integer> segSigns = new ArrayDeque<>();
        int prevSign = 1;
        stack.push(0);
        segSigns.push(1);
        for (int i = 0; i < s.length(); ++i) {
            char c = s.charAt(i);
            if (Character.isDigit(c)) {
                int num = c - '0';
                for(++i; i < s.length() && Character.isDigit(c = s.charAt(i)); ++i) {
                    num = 10 * num + (c - '0');
                }
                stack.push(stack.pop() + num * prevSign);
            }
            if (c == '+') {
                prevSign = 1;
            } else if (c == '-') {
                prevSign = -1;
            } else if (c == '(') {
                stack.push(0);
                segSigns.push(prevSign);
                prevSign = 1;
            } else if (c == ')') {
                int top = stack.pop() * segSigns.pop();
                stack.push(stack.pop() + top);
            }
        }
        return stack.peek();
    }

    //Best solution for this problem. Cannot be generalized to * and /. 
    //Good example showing how to remove the parentheses when the operators are only + and -.
    //https://discuss.leetcode.com/topic/15816/iterative-java-solution-with-stack/6
    //Key is to find out the actual sign before each number after removing all the parentheses,
    //which is equal to the context multiplier times the previous sign.
    //Has to use a stack to store the context multipliers since they are related to each level,
    //and we need to store the ones of previous levels so that they can be recovered.
    //Also pay attention to this case: "1 - (5)". Parenthesis around a number only is also valid.
    public int calculate(String s) {
        Deque<Integer> stack = new ArrayDeque<>();
        stack.push(1);
        int result = 0;
        int prevSign = 1;
        for (int i = 0; i < s.length(); ++i) {
            char c = s.charAt(i);
            if (Character.isDigit(c)) {
                int num = c - '0';
                for(++i; i < s.length(); ++i) {
                    c = s.charAt(i);
                    if (Character.isDigit(c)) {
                        num = num * 10 + (c - '0');
                    } else if (!Character.isWhitespace(c)) {
                        break;
                    }
                }
                result += num * stack.peek() * prevSign;
            }
            if (c == '+') {
                prevSign = 1;
            } else if (c == '-') {
                prevSign = -1;
            } else if (c == '(') {
                stack.push(stack.peek() * prevSign);
                prevSign = 1; //Easy to forget this!
            } else if (c == ')') {
                stack.pop();
            }
        }
        return result;
    }
}