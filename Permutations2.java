public class Permutations2 {
    
    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
    
    private void permute(int[] nums, int id, List<List<Integer>> result) {
        if (id >= nums.length) {
            List<Integer> permutation = new ArrayList<>();
            for (int num : nums) {
                permutation.add(num);
            }
            result.add(permutation);
            return;
        }
        Set<Integer> used = new HashSet<>();
        for(int i = id; i < nums.length; ++i) {
            if (!used.contains(nums[i])) {
                used.add(nums[i]);
                swap(nums, id, i);
                permute(nums, id + 1, result);
                swap(nums, id, i);                
            }
        }    
    }
    
    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        if (nums == null || nums.length == 0) {
            return result;
        }
        permute(nums, 0, result);
        return result;
    }

    //The solutions are similar to those in Permutations I, with addtional de-duplicate operations
}