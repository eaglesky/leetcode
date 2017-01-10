public class SubSets2 {
    
    //Standard DFS solution. 
    //Time complexity: O(1*Cn1 + 2*Cn2 + ... + n*Cnn) = O(n*2^n) time
    //Space complexity: O(n)
    private void dfsSubsets(int[] nums, int id, List<Integer> subset, List<List<Integer>> result) {
        result.add(new ArrayList<Integer>(subset));
        for (int i = id; i < nums.length; ++i) {
            if (i > id && nums[i] == nums[i-1]) {
                continue;
            }
            subset.add(nums[i]);
            dfsSubsets(nums, i+1, subset, result);
            subset.remove(subset.size()-1);
        }
    }
    
    public List<List<Integer>> subsetsWithDup0(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(nums);
        dfsSubsets(nums, 0, new ArrayList<Integer>(), result);
        return result;
    }
    
    //Iterative solution based on induction. The subsets ending with nums[i] can be constructed
    //by adding nums[i] to each of the subsets ending with previous nums.
    //Time complexity is exactly the same as the abovd DFS solution,
    //while this solution requires no extra space!
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(nums);
        result.add(new ArrayList<Integer>());
        int preId = 0;
        for (int i = 0; i < nums.length; ++i) {
            int size = result.size();
            int startId = (i > 0 && nums[i] == nums[i-1]) ? preId : 0;
            for (int j = startId; j < size; ++j) {
                List<Integer> subset = new ArrayList<>(result.get(j));
                subset.add(nums[i]);
                result.add(subset);
            }
            preId = size;
        }
        return result;
    }
}