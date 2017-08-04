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

    //Memoization solution.
    private List<Integer> getResults(String s, List<Integer> ids, Map<List<Integer>, List<Integer>> cache) {
        List<Integer> result = cache.get(ids);
        if (result != null) {
            return result;
        }
        result = new ArrayList<>();
        boolean hasOperator = false;
        for (int i = ids.get(0); i <= ids.get(1); ++i) {
            char c = s.charAt(i);
            if (isOperator(c)) {
                hasOperator = true;
                List<Integer> leftIds = new ArrayList<>(ids);
                leftIds.set(1, i - 1);
                List<Integer> leftResult = getResults(s, leftIds, cache);
                List<Integer> rightIds = new ArrayList<>(ids);
                rightIds.set(0, i + 1);
                List<Integer> rightResult = getResults(s, rightIds, cache); 
                for (int l = 0; l < leftResult.size(); ++l) {
                    for (int r = 0; r < rightResult.size(); ++r) {
                        if (c == '+') {
                            result.add(leftResult.get(l) + rightResult.get(r));
                        } else if (c == '-') {
                            result.add(leftResult.get(l) - rightResult.get(r));
                        } else if (c == '*') {
                            result.add(leftResult.get(l) * rightResult.get(r));
                        }
                    }
                }
            }
        }
        if (!hasOperator) {
            int num = 0;
            for (int i = ids.get(0); i <= ids.get(1); ++i) {
                char c = s.charAt(i);
                if (Character.isDigit(c)) {
                    num = num * 10 + (c - '0');
                } else if (!Character.isWhitespace(c)) {
                    break;
                }
            }
            result.add(num);
        }
        cache.put(ids, result);
        return result;
    }
    
    public List<Integer> diffWaysToCompute(String input) {
        List<Integer> ids = new ArrayList<>();
        ids.add(0);
        ids.add(input.length() - 1);
        return input.isEmpty() ? new ArrayList<>() : getResults(input, ids, new HashMap<>());
    }
}