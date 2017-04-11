public class ProductOfArrayExceptSelf {
    
    //O(n) time and O(1) space
    public int[] productExceptSelf(int[] nums) {
        int[] result = new int[nums.length];
        if (nums.length == 0) {
            return result;
        }
        result[0] = 1;
        for (int i = 1; i < result.length; ++i) {
            result[i] = result[i-1] * nums[i-1];
        }
        int prev = 1;
        for (int i = result.length - 1; i >= 0; --i) {
            result[i] *= prev;
            prev *= nums[i];
        }
        return result;
    }
}