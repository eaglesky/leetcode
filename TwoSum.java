public class TwoSum {

	// If the requirement is to find out all the index pairs,
	// then the value of the map should be list of indices,
	// as the array could have duplicates. 
	// Time complexity in such case would be O(n) + number_of_solutions
	// which must be the optimum 
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