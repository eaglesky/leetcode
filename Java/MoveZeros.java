public class MoveZeros {
    private static void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
    
    public void moveZeroes0(int[] nums) {
        int pre = 0;
        for (int i = 0; i < nums.length; ++i) {
            if (nums[i] != 0) {
                swap(nums, i, pre++);
            }
        }
    }
    
    //Better version
    public void moveZeroes(int[] nums) {
        int id = 0;
        for (int i = 0; i < nums.length; ++i) {
            if (nums[i] != 0) {
                nums[id++] = nums[i];
            }
        }
        for(; id < nums.length; ++id) {
            nums[id] = 0;
        }
    }
}