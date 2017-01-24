public class TrappingRainWater {

    //O(n) time and O(1) space
    public int trap(int[] height) {
        int highestPos = 0;
        int result = 0;
        for (int i = 0; i < height.length; ++i) {
            highestPos = (height[i] > height[highestPos]) ? i : highestPos;
        }
        int highestLeft = 0;
        for (int i = 0; i < highestPos; ++i) {
            if (highestLeft > height[i]) {
                result += highestLeft - height[i];
            }
            highestLeft = Math.max(highestLeft, height[i]);
        }
        int highestRight = 0;
        for (int i = height.length-1; i > highestPos; --i) {
            if (highestRight > height[i]) {
                result += highestRight - height[i];
            }
            highestRight = Math.max(highestRight, height[i]);
        }
        return result;
    }

    //Two pointers approach. Tricky!
    public int trap(int[] height) {
        int left = 0;
        int right = height.length - 1;
        int result = 0;
        int highestLeft = 0, highestRight = 0;
        while (left < right) {
            if (height[left] < height[right]) {
                if (highestLeft > height[left]) {
                    result += highestLeft - height[left];
                }
                highestLeft = Math.max(highestLeft, height[left]);
                left++;
            } else {
                if (highestRight > height[right]) {
                    result += highestRight - height[right];
                }
                highestRight = Math.max(highestRight, height[right]);
                right--;
            }
        }
        return result;
    }
}