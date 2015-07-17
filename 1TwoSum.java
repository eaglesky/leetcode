public class Solution {
    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> visited = new HashMap<Integer, Integer>();
        int[] result = new int[2];
        for (int i = 0; i < nums.length; ++i) {
            int remainder = target - nums[i];
            if (visited.containsKey(remainder)) {
                result[0] = visited.get(remainder);
                result[1] = i + 1;
                return result;
            }
            visited.put(nums[i], i + 1);
        }
        return null;
    }
}