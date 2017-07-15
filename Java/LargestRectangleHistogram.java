public class LargestRectangleHistogram {
    
    //Stack solution. O(n) time and O(n) space
    //For each bar, consider the max rectangle with the height of that bar
    //Scanning from left to right and update max area when the current bar is
    //shorter than previous one.
    //If you consider the max rectangle that is bounded by the current bar height, 
    //you can only get naive solution, which is O(n^2) time
    
    //The below algorithm maintains a stack such that each heights in it are non-decreasing.
    //And element at i-1 always is the first bar that is no higher than i.
    //Therefore for each bar in the stack, the previous one gives the left bound of the 
    //largest possible rectangle with the height of that bar.
    public int largestRectangleArea(int[] heights) {
        int maxArea = 0;
        Deque<Integer> stack = new ArrayDeque<>();
        stack.push(-1);
        for (int i = 0; i <= heights.length;) {
            int id = stack.peek();
            int height = (i < heights.length) ? heights[i] : 0;
            if (id == -1 || height >= heights[id]) {
                stack.push(i++);
            } else {
                stack.pop();
                int prevId = stack.peek();
                maxArea = Math.max(maxArea, heights[id] * (i - prevId - 1));
            }
        }
        return maxArea;
    }

    //Second try.
    public int largestRectangleArea(int[] heights) {
        Deque<Integer> stack = new ArrayDeque<>();
        stack.push(-1);
        int maxArea = 0;
        for (int i = 0; i <= heights.length; ++i) {
            int curHeight = (i < heights.length) ? heights[i] : 0;
            for (; stack.peek() >= 0 && heights[stack.peek()] > curHeight;) {
                int prevId = stack.pop();
                int leftId = stack.peek();
                maxArea = Math.max(maxArea, heights[prevId] * (i - leftId - 1));
            }
            stack.push(i);
        }
        return maxArea;
    }
}