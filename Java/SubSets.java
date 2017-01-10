public class Solution {
    
    //Standard DFS solution. 
    //Time complexity: O(1*Cn1 + 2*Cn2 + ... + n*Cnn) = O(n*2^n) time
    //Space complexity: O(n)
    private void dfsSubsets(int[] nums, int id, List<Integer> subset, List<List<Integer>> result) {
        result.add(new ArrayList<Integer>(subset));
        for (int i = id; i < nums.length; ++i) {
            subset.add(nums[i]);
            dfsSubsets(nums, i+1, subset, result);
            subset.remove(subset.size()-1);
        }
    }
    
    public List<List<Integer>> subsets0(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        dfsSubsets(nums, 0, new ArrayList<Integer>(), result);
        return result;
    }
    
    //Iterative solution based on induction. The subsets ending with nums[i] can be constructed
    //by adding nums[i] to each of the subsets ending with previous nums.
    //Time complexity is exactly the same as the abovd DFS solution,
    //while this solution requires no extra space!
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        result.add(new ArrayList<Integer>());
        for (int num : nums) {
            int size = result.size();
            for (int i = 0; i < size; ++i) {
                List<Integer> subset = new ArrayList<>(result.get(i));
                subset.add(num);
                result.add(subset);
            }
        }
        return result;
    }
    
    //Another iterative is based on bit operation. Not easy to implement to take care of general cases,
    //and its runtime is slightly higher than the above solutions, because for each subset it has to
    //iterate n times.
}