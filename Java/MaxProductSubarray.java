public class MaxProductSubarray {
    
    //DP solution, O(n) time and O(1) space
    public int maxProduct(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        int prevMax = nums[0];
        int prevMin = nums[0];
        int maxProduct = prevMax;
        for (int i = 1; i < nums.length; ++i) {
            int curMaxProduct = nums[i] * prevMax;
            int curMinProduct = nums[i] * prevMin;
            if (nums[i] >= 0) {
                prevMax = Math.max(nums[i], curMaxProduct);
                prevMin = Math.min(nums[i], curMinProduct);
            } else {
                prevMax = Math.max(nums[i], curMinProduct);
                prevMin = Math.min(nums[i], curMaxProduct);
            }
            maxProduct = Math.max(maxProduct, prevMax);
        }
        return maxProduct;
    }
}