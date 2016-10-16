public class ContainerMostWater {

    // O(n) time and O(1) space
    public int maxArea(int[] height) {
        int maxArea = 0;
        for (int start = 0, end = height.length - 1; start < end;) {
            if ((start > 0) && (height[start] <= height[start-1])) {
                start++;
                continue;
            }
            if ((end < height.length-1) && (height[end] <= height[end+1])) {
                end--;
                continue;
            }
            int curArea = (end - start) * Math.min(height[start], height[end]);
            if (curArea > maxArea) {
                maxArea = curArea;
            }
            if (height[start] < height[end]) {
                start++;
            } else {
                end--;
            }
        }
        return maxArea;
    }
}