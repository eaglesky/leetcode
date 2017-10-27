public class SortColors {
    
    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
    
    public void sortColors0(int[] nums) {
        int low = -1;
        int high = nums.length;
        for (int i = 0; i < nums.length;) {
            if (nums[i] == 0) {
                if (i == low) {
                    ++i;
                } else {
                    swap(nums, ++low, i);  
                }
            } else if (nums[i] == 2) {
                if (i == high) {
                    break;
                }
                swap(nums, i, --high);
            } else {
                ++i;
            }
        }
    }
    
    //Two passes robust solution
    //Don't see how it is worse than the following one
    //Easier to come up with and proof
    //O(n) time and O(1) space
    public void sortColors(int[] nums) {
        int i0 = 0;
        for (int i = 0; i < nums.length; ++i) {
            if (nums[i] == 0) {
                swap(nums, i, i0++);
            }
        }
        int i2 = nums.length - 1;
        for (int i = nums.length - 1; i >= 0 && nums[i] != 0; --i) {
            if (nums[i] == 2) {
                swap(nums, i, i2--);
            }
        }
    }

    //Using swap cannot keep the relative order of same elements.
    //To make the sort stable, we have to use asignments like in "move zeros".

    //One pass solution
    //O(n) time and O(1) space
    public void sortColors(int[] nums) {
        int low = 0;
        int high = nums.length - 1;
        for (int i = 0; i <= high;) {
            if (nums[i] == 2) {
                swap(nums, i, high--);
            } else if (nums[i] == 0) {
                swap(nums, i++, low++);
            } else {
                ++i;
            }
        }
    }
}