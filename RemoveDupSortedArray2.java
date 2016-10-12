public class RemoveDupSortedArray2 {

    // First try
    public int removeDuplicates(int[] nums) {
        int count = 1;
        int i = 0;
        for (int j = 1; j < nums.length; ++j) {
            if (nums[j] == nums[j-1]) {
                if (count < 2) {
                    count++;
                    nums[++i] = nums[j];
                }
            } else {
                nums[++i] = nums[j];
                count = 1;
            }
        }
        return (nums.length == 0) ? 0 : i+1;
    }

    // Simpler solution:
    // Just imagine as if you are copying the elements to a new array
    public int removeDuplicates(int[] nums) {
        int i = 0;
        for (int num : nums) {
            if (i < 2 || (num != nums[i-2])) {
                nums[i++] = num;
            }
        }
        return i;
    }
}