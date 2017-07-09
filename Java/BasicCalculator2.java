public class BasicCalculator2 {
    
    private static void computeWithStacks(Deque<Character> operators, Deque<Integer> operands) {
            if (!operators.isEmpty() && operands.size() >= 2) {
                char operator = operators.pop();
                int operand2 = operands.pop();
                int operand1 = operands.pop();
                operands.push(calculate(operand1, operand2, operator));               
            }
    }
    
    private static int calculate(int operand1, int operand2, char operator) {
        switch(operator) {
            case '+': return operand1 + operand2;
            case '-': return operand1 - operand2;
            case '*': return operand1 * operand2;
            case '/': return operand1 / operand2;
            default: return 0;
        }
    }
    
    //First try, O(n) time and O(n) space 
    public int calculate0(String s) {
        Deque<Character> operators = new ArrayDeque<>();
        Deque<Integer> operands = new ArrayDeque<>();
        for (int i = 0; i < s.length();) {
            char c = s.charAt(i);
            if (Character.isDigit(c)) {
                int j = i + 1;
                for (; j < s.length() && Character.isDigit(s.charAt(j)); ++j);
                String curStr = s.substring(i, j);
                int curValue = Integer.parseInt(curStr);
                i = j;
                if (!operators.isEmpty() && (operators.peek() == '*' || operators.peek() == '/')) {
                    char preOperator = operators.pop();
                    int preOperand = operands.pop();
                    operands.push(calculate(preOperand, curValue, preOperator));
                } else {
                    operands.push(curValue);
                }
                continue;
            } else if (c == '+' || c == '-' || c == '*' || c == '/') {
                if ((c == '+' || c == '-') && !operators.isEmpty()) {
                    computeWithStacks(operators, operands);
                }
                operators.push(c); 
            }
            ++i;
        }
        computeWithStacks(operators, operands);
        return operands.pop();
    }
    
    //Best solution, O(n) time and O(1) space
    //https://discuss.leetcode.com/topic/41118/simple-c-solution-beats-85-submissions-with-detailed-explanations
    public int calculate(String s) {
        int result = 0;
        int temp = 0;
        char preOperator = '+';
        for (int i = 0; i < s.length();) {
            char c = s.charAt(i);
            if (Character.isDigit(c)) {
                int num = 0;
                for (; i < s.length() && Character.isDigit(s.charAt(i)); ++i) {
                    num = 10 * num + (s.charAt(i) - '0');
                }
                switch (preOperator) {
                    case '+': result += temp;
                              temp = num;
                              break;
                    case '-': result += temp;
                              temp = -num;
                              break;
                    case '*': temp *= num;
                              break;
                    case '/': temp /= num;
                              break;
                }
                continue;
            } else if (c == '+' || c == '-' || c == '*' || c == '/') {
                preOperator = c;
            }
            ++i;
        }
        result += temp;
        return result;
    }
    
    //Another solution uses a stack to stores the products/quotients and 
    //finally use one pass to add them together. Similar to the above thought, but with O(n) space
    //https://discuss.leetcode.com/topic/16935/share-my-java-solution
}