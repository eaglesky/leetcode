public class EvaluateReversePolishNotation {
    
    private static int calculate(int a, int b, String operator) {
        switch (operator) {
            case "+" : return a + b;
            case "-" : return a - b;
            case "*" : return a * b;
            case "/" : return a / b;
        }
        return 0;
    }
    
    public int evalRPN(String[] tokens) {
        Deque<Integer> stack = new ArrayDeque<>();
        for (String token : tokens) {
            if (token.equals("+") || token.equals("-")
            || token.equals("*") || token.equals("/")) {
                int a, b;
                if (stack.peek() != null) {
                    b = stack.pop();
                } else {
                    return 0;
                }
                if (stack.peek() != null) {
                    a = stack.pop();
                } else {
                    return 0;
                }
                stack.push(calculate(a, b, token));
            } else {
                stack.push(Integer.parseInt(token));
            }
        }
        return stack.pop();
    }
}