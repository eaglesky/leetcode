public class FindMinimumInRotatedSortedArray {
    public int findMin(int[] nums) {
        int low = 0;
        int high = nums.length - 1;
        int minId = -1;
        while (low <= high) {
            if (nums[low] <= nums[high]) {
                return nums[low];
            }
            int mid = low + ((high - low) >> 1);
            if (nums[mid] >= nums[low]) {
                low = mid + 1;
            } else {
                high = mid;
            }
        }
        return minId == -1 ? Integer.MAX_VALUE : nums[minId];
    }

    //Better solution
    public int findMin(int[] nums) {
        int low = 0;
        int high = nums.length - 1;
        while (low < high) {
            int mid = low + ((high - low) >> 1);
            if (nums[mid] <= nums[high]) {
                high = mid;
            } else {
                low = mid + 1;
            }
        }
        return nums[low];
    }
}