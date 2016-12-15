public class SearchRotatedSortedArray2 {


    public boolean search0(int[] nums, int target) {
        int low = 0;
        int high = nums.length - 1;
        while (low <= high) {
            if (nums[low] == nums[high]) {
                if (target == nums[low]) {
                    return true;
                } 
                for (;low <= high && nums[low] == nums[high]; ++low);
                continue;
            }
            int mid = low + (high - low) / 2;
            if (target == nums[mid]) {
                return true;
            }
            if (nums[mid] >= nums[low]) {
                if (target < nums[mid] && target >= nums[low]) {
                    high = mid - 1;
                } else {
                    low = mid + 1;
                }
            } else {
                if (target > nums[mid] && target <= nums[high]) {
                    low = mid + 1;
                } else {
                    high = mid - 1;
                }
            }
        }
        return false;
    }

    //Best solution, slight change from the solution of SearchInRotatedSortedArray 1
    //O(n) time and O(1) space
    public boolean search(int[] nums, int target) {
        int low = 0;
        int high = nums.length - 1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (target == nums[mid]) {
                return true;
            }
            if (nums[mid] > nums[low]) {
                if (target < nums[mid] && target >= nums[low]) {
                    high = mid - 1;
                } else {
                    low = mid + 1;
                }
            } else if (nums[mid] < nums[low]){
                if (target > nums[mid] && target <= nums[high]) {
                    low = mid + 1;
                } else {
                    high = mid - 1;
                }
            } else {
                low++;
            }
        }
        return false;
    }
}