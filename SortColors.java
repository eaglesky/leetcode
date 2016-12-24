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
    
    //Best solution
    //O(n) time and O(1) space
    public void sortColors(int[] nums) {
        int low = -1;
        int high = nums.length;
        for (int i = 0; i < high;) {
            if (nums[i] == 2) {
                swap(nums, i, --high);
            } else if (nums[i] == 0) {
                swap(nums, i++, ++low); 
            } else {
                ++i;
            }
        }
    }
}