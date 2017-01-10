public class ThreeSumClosest {

    // O(n^2) time and O(1) space
    public int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums);
        int closestSum = 0;
        int diff = Integer.MAX_VALUE;
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
                    return target;
                } else if (sum < other) {
                    start++;
                } else {
                    end--;
                }
                int curDiff = Math.abs(sum+nums[i]-target);
                if (curDiff < diff) {
                    diff = curDiff;
                    closestSum = sum+nums[i];
                }
            }
        }
        return closestSum;
    }
}