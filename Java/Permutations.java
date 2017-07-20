public class Permutations {
    private void dfs(int[] nums, int id, Set<Integer> used, Integer[] curPermutation, List<List<Integer>> result) {
        if (id == nums.length) {
            result.add(new ArrayList<Integer>(Arrays.asList(curPermutation)));
            return;
        }
        for (int num : nums) {
            if (!used.contains(num)) {
                used.add(num);
                curPermutation[id] = num;
                dfs(nums, id+1, used, curPermutation, result);
                used.remove(num);
            }
        }
    }
    
    //Standard DFS solution, O(n^n) time and O(n) space
    public List<List<Integer>> permute0(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        if (nums.length == 0) {
            return result;
        }
        dfs(nums, 0, new HashSet<Integer>(), new Integer[nums.length], result);
        return result;
    }
    
    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
    
    private void dfs2(int[] nums, int id, List<List<Integer>> result) {
        if (id == nums.length) {
            List<Integer> curPermutation = new ArrayList<>();
            for (int num : nums) {
                curPermutation.add(num);
            }
            result.add(curPermutation);
            return;
        }
        
        for (int i = id; i < nums.length; ++i) {
            swap(nums, id, i);
            dfs2(nums, id + 1, result);
            //Swapping back is necessary because dfs2 should not change the 
            //order of elements.
            //Consider 3, 4, 5. If we don't swap it back,
            //then we get 4, 3, 5 -> 4, 5, 3
            //then in the first recursion, we swap the third one
            //with the first one and get 3, 5, 4, which is duplicate
            swap(nums, id, i);
        }
    }
    
    //Another back tracking solution based on swapping
    //O(n*n!) time and O(n) space
    //Most efficient solution
    public List<List<Integer>> permute1(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        if (nums.length == 0) {
            return result;
        }
        dfs2(nums, 0, result);
        return result;
    }
    
    //Iterative solution. The permutation of first n elements can be created
    //from the permutation of first n-1 elements by inserting the nth element 
    //to different slots. https://discuss.leetcode.com/topic/6377/my-ac-simple-iterative-java-python-solution
    //However due to the frequent list copy, I don't think this solution is very efficient.
    
    //Iterative solution using Next Permutation. O(n*n!) time and O(1) extra space
    //Works even if nums has duplicates!
    //Basic proof: let i be the index of first element from left that differs between
    //the next permutation and current permutation.
    //j be the index of first element from right that drops.
    //i <= j must be true, since the elements after j are non-increasing and can't be
    //increased for that part. Since next permutation should be the closest to the 
    //current permutation, i == j, and we choose the smallest element larger than jth
    //one to swap it, and make the rest increasing to get the next permutation.
    private boolean nextPermutation(int[] nums) {
        int i = nums.length - 2;
        for(; i >= 0 && nums[i] > nums[i+1]; --i);
        if (i < 0) {
            return false;
        }
        int j = nums.length - 1;
        for(; j > i && nums[j] < nums[i]; --j);
        swap(nums, i, j);
        for(int start = i+1, end = nums.length-1; start < end; ++start, --end) {
            swap(nums, start, end);
        }
        return true;
    }
    
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        if (nums == null || nums.length == 0) {
            return result;
        }
        Arrays.sort(nums);
        do {
            List<Integer> permutation = new ArrayList<>();
            for(int num : nums) {
                permutation.add(num);
            }
            result.add(permutation);
        } while (nextPermutation(nums));
        return result;
    }
}