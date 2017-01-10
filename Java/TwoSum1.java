import java.util.*;

public class TwoSum1 {
    public static int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> visited = new HashMap<Integer, Integer>();
        int[] result = new int[2];
        if (nums == null)
        	return null;

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

    public static void main(String[] args) {
    	int[] testNums = {2, 7, 11, 15};
    	//int[] testNums = null;
    	int testTarget = 9;
    	int[] result = twoSum(testNums, testTarget);

    	if (result != null)
    		System.out.println("Result: " + result[0] + ", " + result[1]);
    	else
    		System.out.println("No solution found");
    }
}