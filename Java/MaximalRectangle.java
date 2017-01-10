public class MaximalRectangle {
    
    //Use the algorithm of largest rectangle in histogram.
    //O(mn) time and O(n) space
    public int maximalRectangle(char[][] matrix) {
        if (matrix.length == 0 || matrix[0].length == 0) {
            return 0;
        }
        int[] heights = new int[matrix[0].length + 1];
        int maxArea = 0;
        for (int i = 0; i < matrix.length; ++i) {
            Deque<Integer> stack = new ArrayDeque<>();
            stack.push(-1);
            for (int j = 0; j <= matrix[i].length; ++j) {
                heights[j] = (j == matrix[i].length || matrix[i][j] == '0') ? 0 : heights[j] + 1;                
            }
            for (int j = 0; j <= matrix[i].length;) {
                int preId = stack.peek();
                if (preId == -1 || heights[j] >= heights[preId]) {
                    stack.push(j++);
                } else {
                    stack.pop();
                    int preId2 = stack.peek();
                    maxArea = Math.max(maxArea, heights[preId]*(j - preId2 - 1));
                }
            }
        }
        return maxArea;
    }

    //See the c++ solution for the DP approach. 
    //No better than the above solution
}