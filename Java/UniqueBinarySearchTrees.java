public class UniqueBinarySearchTrees {
    
    //O(n^2) time and O(n) space
    public int numTrees(int n) {
        int[] nums = new int[n + 1];
        nums[0] = 1;
        for (int i = 1; i <= n; ++i) {
            int half = 0;
            for (int j = 0; j < (i >> 1); ++j) {
                half += nums[j] * nums[i - 1 - j];
            }
            nums[i] = half << 1;
            if (i % 2 != 0) {
                nums[i] += nums[i >> 1] * nums[i >> 1];
            }
        }
        return nums[n];
    }
}