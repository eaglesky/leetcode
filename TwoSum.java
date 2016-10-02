public class TwoSum {
    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> used = new HashMap<Integer, Integer>();
        for (int i = 0; i < nums.length; ++i) {
            int other = target - nums[i];
            if (used.containsKey(other)) {
                return new int[]{used.get(other), i};
            }
            used.put(nums[i], i);
        }
        return null;
    }
}