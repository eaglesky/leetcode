public class FirstMissingPos {
    
    private boolean isValid(int a, int max) {
        return (a <= max) && (a > 0);
    }
    
    private void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
    
    //This is O(n) since each element will be checked against the destination
    //for at most once. 
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

    //Second try. The first one is easier to write since swap can make 
    //the end condition check simpler.
    public int firstMissingPositive(int[] nums) {
        for (int i = 0; i < nums.length; ++i) {
            int num = nums[i];
            while(num >= 1 && num <= nums.length && (nums[num-1] != num)) {
                int newNum = nums[num - 1];
                nums[num - 1] = num;
                num = newNum;
            }
        }
        for (int i = 0; i < nums.length; ++i) {
            if (nums[i] != i + 1) {
                return i + 1;
            }
        }
        return nums.length + 1;
    }
}