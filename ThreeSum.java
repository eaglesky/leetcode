public class ThreeSum {

    // O(n^2) time and O(1) space
    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> results = new ArrayList<>();
        int target = 0;
        for (int i = 0; i < nums.length; ++i) {
            if ((i < nums.length-1) && (nums[i] == nums[i+1])) {
                continue;
            }
            int start = 0;
            int end = i - 1;
            int other = target - nums[i];
            while (start < end) {
                if ((start > 0) && (nums[start] == nums[start-1])) {
                    start++;
                    continue;
                }
                if ((end < i-1) && (nums[end] == nums[end+1])) {
                    end--;
                    continue;
                }
                int sum = nums[start] + nums[end];
                if (sum == other) {
                    List<Integer> result = new ArrayList<>();
                    result.add(nums[start]);
                    result.add(nums[end]);
                    result.add(nums[i]);
                    results.add(result);
                    end--;
                } else if (sum < other) {
                    start++;
                } else {
                    end--;
                }
            }
        }
        return results;
    }
}