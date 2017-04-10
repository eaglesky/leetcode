public class RandomPickIndex {

    private final Map<Integer, List<Integer>> num2Ids = new HashMap<>();
    private final Random rand = new Random();
    
    //O(n) time to initialize, O(1) time to pick.
    //O(n) extra space.
    public Solution0(int[] nums) {
        for (int i = 0; i < nums.length; ++i) {
            List<Integer> ids = num2Ids.get(nums[i]);
            if (ids == null) {
                ids = new ArrayList<>();
                num2Ids.put(nums[i], ids);
            }
            ids.add(i);
        }
    }
    
    public int pick0(int target) {
        List<Integer> ids = num2Ids.get(target);
        if (ids == null) {
            return -1;
        }
        return ids.get(rand.nextInt(ids.size()));
    }

    //Reservoir sampling solution
    //O(1) time to initialize, O(n) time to pick.
    //O(1) extra space
    private final int[] nums;
    public Solution(int[] nums) {
        this.nums = nums;
    }
    
    public int pick(int target) {
        int reservoir = -1;
        int count = 0;
        for (int i = 0; i < nums.length; ++i) {
            if (nums[i] == target && rand.nextInt(++count) == 0) {
                reservoir = i;
            }
        }
        return reservoir;
    }
}

/**
 * Your Solution object will be instantiated and called as such:
 * Solution obj = new Solution(nums);
 * int param_1 = obj.pick(target);
 */