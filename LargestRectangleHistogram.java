public class LargestRectangleHistogram {
    
    //Stack solution. O(n) time and O(n) space
    //For each bar, consider the max rectangle with the height of that bar
    //Scanning from left to right and update max area when the current bar is
    //shorter than previous one.
    //If you consider the max rectangle that ends with the current bar, 
    //you can only get naive solution, which is O(n^2) time
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
}