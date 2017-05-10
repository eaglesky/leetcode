public class TargetSum {
    
    //O(2^n) time and O(n*x) space dfs solution with memoization.
    //https://discuss.leetcode.com/topic/76373/evolve-from-brute-force-to-dp
    private int dfs(int[] nums, int start, int sum, List<Map<Integer, Integer>> cache) {
        if (start >= nums.length) {
            return (sum == 0) ? 1 : 0;
        }
        if (start >= cache.size()) {
            cache.add(new HashMap<Integer, Integer>());
        }
        Integer count = cache.get(start).get(sum);
        if (count != null) {
            return count;
        }
        int newCount = dfs(nums, start + 1, sum - nums[start], cache)
                     + dfs(nums, start + 1, sum + nums[start], cache);
        cache.get(start).put(sum, newCount);
        return newCount;
    }
    
    public int findTargetSumWays0(int[] nums, int S) {
        return dfs(nums, 0, S, new ArrayList<Map<Integer, Integer>>());
    }
    
    //Say the sum of positives is sum_p, then
    //sum_p - (sum_all - sum_p) = S
    //sum_p = (S + sum_all) / 2
    //This is why this problem can be converted into finding the subset sum
    //that is equal to a positve sum_p
    //Time is O(n*sum_all), space is O(sum_all).
    //https://discuss.leetcode.com/topic/76243/java-15-ms-c-3-ms-o-ns-iterative-dp-solution-using-subset-sum-with-explanation
    private static int findPositiveTarget(int[] nums, int target) {
        int[] dp = new int[target + 1];
        dp[0] = 1;
        for (int l = 1; l <= nums.length; ++l) {
            for (int sum = target; sum >= nums[l-1]; --sum) {
                dp[sum] += dp[sum - nums[l-1]];
            }
        }
        return dp[target];
    }
    
    public int findTargetSumWays(int[] nums, int S) {
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        if (S < -sum || S > sum || ((S + sum) & 1) != 0) {
            return 0;
        }
        int target = (S + sum) >> 1;
        return findPositiveTarget(nums, target);
    }
}