import java.util.*;

public class SearchInsertPosition {

	//Binary search to find out the first element that is greater than
	//or equal to the target
	//O(logn) time and O(1) space
    public static int searchInsert(int[] nums, int target) {
        int low = 0;
        int high = nums.length;
        while (low < high) {
            int mid = low + ((high - low) >> 1);
            if (target > nums[mid]) {
                low = mid + 1;
            } else {
                high = mid;
            }
        }
        return low;
    }

    //Another binary search. 
    public int searchInsert2(int[] nums, int target) {
        int low = 0;
        int high = nums.length - 1;
        while (low <= high) {
            int mid = low + ((high - low) >> 1);
            if (target > nums[mid]) {
                low = mid + 1;
            } else if (target < nums[mid]){
                high = mid - 1;
            } else {
                return mid;
            }
        }
        return low;
    }

    //Returns the last element that is less than or equal to
    // the target. -1 if no element found.
    public static int lastLessEqual(int[] nums, int target) {
    	int low = -1;
    	int high = nums.length - 1;
    	while (low < high) {
    		int mid = high - ((high - low) >> 1);
    		if (target < nums[mid]) {
    			high = mid - 1;
    		} else {
    			low = mid;
    		}
    	}
    	return high;
    }

    public static void main(String[] args) {
    	int[][] tests = {
    		{},
    		{1},
    		{1, 3, 3, 5, 5, 6},
    		{2, 3, 3, 3, 3, 4, 5, 5, 6, 7, 7}
    	};
    	int[] targets = {-2, 1, 3, 7, 8};
    	for (int[] test : tests) {
    		System.out.println("Array content: " + Arrays.toString(test));
    		for (int target : targets) {
    			System.out.println("Target: " + target + "; " + searchInsert(test, target)
    				+ ", " + lastLessEqual(test, target));
    		}
    	}
    }

}