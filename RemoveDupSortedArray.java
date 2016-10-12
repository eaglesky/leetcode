public class RemoveDupSortedArray {
    public int removeDuplicates(int[] nums) {
        int i = 0;
        for (int j = 1; j < nums.length; ++j) {
            if (nums[j] != nums[j-1]) {
                nums[++i] = nums[j];
            }
        }
        return (nums.length == 0) ? 0 : i+1;
    }
}