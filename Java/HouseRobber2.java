public class HouseRobber2 {
    
    private static int robPartial(int[] nums, int start, int end) {
        if (start > end || start < 0 || end >= nums.length) {
            return 0;
        }
        int preNo = 0;
        int preYes = 0;
        for (int i = start; i <= end; ++i) {
            int curNo = Math.max(preNo, preYes);
            int curYes = preNo + nums[i];
            preNo = curNo;
            preYes = curYes;
        }
        return Math.max(preNo, preYes);
    }
    
    //O(n) time and O(1) space
    //Use algorithm from House Robber I
    public int rob(int[] nums) {
        if (nums.length == 0) {
            return 0;
        } else if (nums.length == 1) {
            return nums[0];
        }
        return Math.max(robPartial(nums, 1, nums.length - 1),
                        robPartial(nums, 0, nums.length - 2));
    }
}