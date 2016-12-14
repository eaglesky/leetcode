public class SearchForRange {
    
    private int searchBound(int[] nums, int target, boolean isLowerBound) {
        int low = 0;
        int high = nums.length - 1;
        while(low <= high) {
            int mid = (isLowerBound) ? low + (high - low) / 2 : high - (high - low) / 2;
            if (target > nums[mid]) {
                low = mid + 1;
            } else if (target < nums[mid]) {
                high = mid - 1;
            } else {
                if (isLowerBound) {
                    high = mid;
                } else {
                    low = mid;
                }
                if (high == low) {
                    return low;
                }
            }
        }
        return -1;
    }
    
    //Another solution is to call firstGreaterEqual to find the lowerBound,
    //and then call lastLessEqual to find the upperBound.
    
    public int[] searchRange(int[] nums, int target) {
        int lowerBound = searchBound(nums, target, true);
        int upperBound = searchBound(nums, target ,false);
        return new int[]{lowerBound, upperBound};
    }
}