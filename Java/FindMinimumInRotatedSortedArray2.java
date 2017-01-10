public class FindMinimumInRotatedSortedArray2 {
    public int findMin0(int[] nums) {
        int low = 0;
        int high = nums.length - 1;
        int minId = -1;
        while (low <= high) {
            if (low == high || nums[low] < nums[high]) {
                return nums[low];
            }
            int mid = low + ((high - low) >> 1);
            if (nums[mid] > nums[low]) {
                low = mid + 1;
            } else if (nums[mid] < nums[low]){
                high = mid;
            } else {
                low++;
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
            if (nums[mid] < nums[high]) {
                high = mid;
            } else if (nums[mid] > nums[high]) {
                low = mid + 1;
            } else {
                high--;
            }
        }
        return nums[low];
    }
}