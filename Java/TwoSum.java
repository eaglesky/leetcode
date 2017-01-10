import java.util.*;

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

    // Find out all the unique two sums
    // O(n) time and O(n) space
    public static List<int[]> twoSumUniqueAll(int[] nums, int target) {
        List<int[]> result = new ArrayList<>();
        Map<Integer, Integer> used = new HashMap<>();
        Set<Integer> usedValues = new HashSet<>();
        for (int i = 0; i < nums.length; ++i) {
            int otherValue = target - nums[i];
            Integer prevId = used.get(otherValue);
            if (prevId != null && !usedValues.contains(nums[i])) {
                result.add(new int[]{prevId.intValue(), i});
                usedValues.add(nums[i]);
                usedValues.add(otherValue);
            }
            used.put(nums[i], i);
        }
        return result;
    }

    public static void main (String[] args) {
        List<int[]> result = twoSumUniqueAll(new int[]{2, 3, 2, 1, 1, 4, 3}, 5);
        List<int[]> result2 = twoSumUniqueAll(new int[]{2, 2, 2, 2, 2}, 4);
        for (int[] indices : result2) {
            System.out.println(indices[0] + ", " + indices[1]);
        }
    }
}