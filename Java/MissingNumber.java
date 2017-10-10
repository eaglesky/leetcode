class MissingNumber {
    
    //https://discuss.leetcode.com/topic/23427/3-different-ideas-xor-sum-binary-search-java-code
    
    //Sum
    public int missingNumber0(int[] nums) {
        int n = nums.length;
        //Could overflow if n is large
        int totalSum = (1 + n) * n / 2;
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        return totalSum - sum;
    }
    
    //Can also use algorithm in finding mising positive, but is really overkill for this.
    
    //XOR
    public int missingNumber1(int[] nums) {
        int xor = nums.length;
        for (int i = 0; i < nums.length; ++i) {
            xor ^= i;
            xor ^= nums[i];
        }
        return xor;
    }
    
    //Binary search
    public int missingNumber(int[] nums) {
        Arrays.sort(nums);
        int low = 0;
        int high = nums.length;
        while (low < high) {
            int mid = low + (high - low) / 2;
            if (nums[mid] == mid) {
                low = mid + 1;
            } else {
                high = mid;
            }
        }
        return low;
    }
}