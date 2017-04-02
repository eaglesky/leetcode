public class DiffWaysAddParentheses {
    
    //Can be improved using memoization
    //https://discuss.leetcode.com/topic/42550/java-simple-solution-beats-95
    //Exponational time.
    private static boolean isOperator(char c) {
        return (c == '+') || (c == '-') || (c == '*');
    }
    
    private static List<Integer> computeRecur(String str, int start, int end) {
        List<Integer> result = new ArrayList<>();
        for (int i = start; i <= end; ++i) {
            char c = str.charAt(i);
            if (isOperator(c)) {
                List<Integer> leftResult = computeRecur(str, start, i - 1);
                List<Integer> rightResult = computeRecur(str, i + 1, end);
                for (int l = 0; l < leftResult.size(); ++l) {
                    int left = leftResult.get(l);
                    for (int r = 0; r < rightResult.size(); ++r) {
                        int right = rightResult.get(r);
                        if (c == '+') {
                            result.add(left + right);
                        } else if (c == '-') {
                            result.add(left - right);
                        } else if (c == '*') {
                            result.add(left * right);
                        }
                    }
                }
            }
        }
        if (result.isEmpty()) {
            result.add(Integer.parseInt(str.substring(start, end+1)));
        }
        return result;
    }
    
    public List<Integer> diffWaysToCompute(String input) {
        return computeRecur(input, 0, input.length() - 1);
    }
}