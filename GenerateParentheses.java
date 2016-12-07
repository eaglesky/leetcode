public class GenerateParenthesis {
    
    private void dfs(int pos, int leftUnused, int rightUnused, char[] curStrArray, List<String> result) {
        if (leftUnused == 0 && rightUnused == 0) {
            result.add(new String(curStrArray));
            return;
        }
        if (leftUnused > 0) {
            curStrArray[pos] = '(';
            dfs(pos + 1, leftUnused - 1, rightUnused, curStrArray, result);
        }
        if (leftUnused < rightUnused) {
            curStrArray[pos] = ')';
            dfs(pos + 1, leftUnused, rightUnused - 1, curStrArray, result);
        }
    }
    
    // DFS solution
    public List<String> generateParenthesis0(int n) {
        List<String> result = new ArrayList<>();
        if (n <= 0) {
            return result;
        }
        dfs(0, n, n, new char[2*n], result);
        return result;
    }
    
    // Iterative solution, just a simulation of the above recursive solution
    public List<String> generateParenthesis(int n) {
        List<String> result = new ArrayList<>();
        if (n <= 0) {
            return result;
        }
        Deque<String> strStack = new ArrayDeque<>();
        Deque<Integer> leftParenCounts = new ArrayDeque<>();
        strStack.push("(");
        leftParenCounts.push(1);
        while (!strStack.isEmpty()) {
            String topStr = strStack.pop();
            int leftParenNum = leftParenCounts.pop();
            int rightParenNum = topStr.length() - leftParenNum;
            if (leftParenNum == n && rightParenNum == n) {
                result.add(topStr);
                continue;
            }
            if (leftParenNum < n) {
                strStack.push(topStr + "(");
                leftParenCounts.push(leftParenNum + 1);
            }
            if (leftParenNum > rightParenNum) {
                strStack.push(topStr + ")");
                leftParenCounts.push(leftParenNum);
            }

        }
        return result;
    }

    // DP solution ?
}