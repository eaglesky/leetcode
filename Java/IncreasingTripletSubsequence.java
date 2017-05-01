public class IncreasingTripletSubsequence {
    
    //O(n) time and O(1) space
    //Can be easily changed to return the triplet.
    public boolean increasingTriplet(int[] nums) {
        if (nums.length < 3) {
            return false;
        }
        int prevMin = Math.min(nums[0], nums[1]);
        int prevLowestPeakId = -1;
        if (nums[0] < nums[1]) {
            prevLowestPeakId = 1;
        }
        for (int i = 2; i < nums.length; ++i) {
            if (prevLowestPeakId >= 0 && nums[prevLowestPeakId] < nums[i]) {
                return true;
            }
            if (nums[i] > prevMin && ((prevLowestPeakId == -1) || (nums[prevLowestPeakId] > nums[i]))) {
                prevLowestPeakId = i;
            }
            prevMin = Math.min(prevMin, nums[i]);
        }
        return false;
    }
}