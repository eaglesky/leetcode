class MajorityElement {
    
    //O(n) time and O(1) space
    public int majorityElement(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int num = nums[0];
        int count = 1;
        for (int i = 1; i < nums.length; ++i) {
            if (nums[i] == num) {
                count++;
            } else if (count > 0) {
                count--;
            } else {
                num = nums[i];
                count++;
            }
        }
        return num;
    }
}