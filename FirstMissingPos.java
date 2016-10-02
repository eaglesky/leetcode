public class FirstMissingPos {
    
    private boolean isValid(int a, int max) {
        return (a <= max) && (a > 0);
    }
    
    private void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
    
    public int firstMissingPositive(int[] nums) {
        int max = nums.length;
        for (int i = 0; i < max; ++i) {
            for (; isValid(nums[i], max) && nums[nums[i]-1]!= nums[i]; ) {
                swap(nums, i, nums[i]-1);
            }
        }
        for (int i = 0; i < max; ++i) {
            if (nums[i] != i+1) {
                return i+1;
            }
        }
        return max+1;
    }
}