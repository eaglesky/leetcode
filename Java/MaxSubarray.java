public class MaxSubarray {
    
    class Sums {
        int maxSumPrefix;
        int maxSumSuffix;
        int maxSum;
        int sum;
        
        public Sums() {}
        public Sums(int maxSumLeft, int maxSumRight, int maxSum, int sum) {
            this.maxSumPrefix = maxSumLeft;
            this.maxSumSuffix = maxSumRight;
            this.maxSum = maxSum;
            this.sum = sum;
        }
    }
    
    private Sums maxSubArrayRecursive(int[] nums, int low, int high) {
        if (low == high) {
            return new Sums(nums[low], nums[low], nums[low], nums[low]);
        }
        int mid = low + ((high - low) >> 1);
        Sums sums1 = maxSubArrayRecursive(nums, low, mid);
        Sums sums2 = maxSubArrayRecursive(nums, mid + 1, high);
        
        Sums sums = new Sums();
        sums.maxSumPrefix = Math.max(sums1.maxSumPrefix, sums1.sum + sums2.maxSumPrefix);
        sums.maxSumSuffix = Math.max(sums2.maxSumSuffix, sums2.sum + sums1.maxSumSuffix);
        sums.maxSum = Math.max(Math.max(sums1.maxSum, sums2.maxSum), sums1.maxSumSuffix + sums2.maxSumPrefix);
        sums.sum = sums1.sum + sums2.sum;
        
        return sums;
    }
    
    //Divide and Conquer solution. O(n) time and O(logn) space
    public int maxSubArray0(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        Sums sums = maxSubArrayRecursive(nums, 0, nums.length - 1);
        return sums.maxSum;
    }
    
    //DP solution, O(n) time and O(1) space
    public int maxSubArray1(int[] nums) {
        int prev = 0;
        int result = Integer.MIN_VALUE;
        for (int num : nums) {
            int cur = (prev <= 0) ? num : num + prev;
            prev = cur;
            result = Math.max(cur, result);
        }
        return result;
    }
    
    //Slight improvement of above:
    //Easier to come up with in interview
    public int maxSubArray(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int prev = 0;
        int maxSum = Integer.MIN_VALUE;
        for (int num : nums) {
            int cur = Math.max(num, num + prev);
            maxSum = Math.max(maxSum, cur);
            prev = cur;
        }
        return maxSum;
    }

    //Greedy algorithm. Same performance as above.
    //sum is the max sum ending with current element.
    public int maxSubArray(int[] nums) {
        int result = Integer.MIN_VALUE;
        int sum = 0;
        for (int num : nums) {
            sum = (sum <= 0) ? num : sum + num;
            result = Math.max(sum, result);
        }
        return result;
    }

    public static void main(String[] args) {
    	int[][] tests = {
    		{-32,-54,-36,62,20,76,-1,-86,-13,38}
    	};
    	MaxSubarray ms = new MaxSubarray();
    	for (int[] test : tests) {
    		System.out.println(ms.maxSubArray(test));
    		System.out.println(ms.maxSubArray0(test));
    		System.out.println("");
    	}
    }
    
}