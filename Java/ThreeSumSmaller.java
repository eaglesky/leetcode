/**
 * Given an array of n integers nums and a target, find the number of index triplets i, j, k
 * with 0 <= i < j < k < n that satisfy the condition nums[i] + nums[j] + nums[k] < target.
 * For example, given nums = [-2, 0, 1, 3], and target = 2.
 * Return 2. Because there are two triplets which sums are less than 2:
 * [-2, 0, 1]
 * [-2, 0, 3]
 * Follow up:
 * Could you solve it in O(n^2) runtime?
 */

class ThreeSumSmaller {

	//Two pointers, O(n^2) time and O(1) space
	public int threeSumSmaller(int[] nums, int target) {
		Arrays.sort(nums);
		int count = 0;
		for (int i = 2; i < nums.length; ++i) {
			int remainder = target - nums[i];
			for (int start = 0, end = i - 1; start < end;) {
				if (nums[start] + nums[end] >= remainder) {
					end--;
				} else {
					count += end - start;
					start++;
				}
			}
		}
		return count;
	}
}