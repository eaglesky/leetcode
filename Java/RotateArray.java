class RotateArray {
    
    //O(n) time and O(1) space
    private void reverseArray(int[] nums, int start, int end) {
        while (start < end) {
            int temp = nums[start];
            nums[start] = nums[end];
            nums[end] = temp;
            start++;
            end--;
        }    
    }
    
    public void rotate(int[] nums, int k) {
        if (nums == null || nums.length == 0 || k <= 0) {
            return;
        }
        int n = nums.length;
        k = k % n;
        if (k == 0) {
            return;
        }
        reverseArray(nums, 0, n - 1);
        reverseArray(nums, 0, k - 1);
        reverseArray(nums, k, n - 1);
    }
    
    //Another solution uses O(k) extra space, but faster
    //Just copy k elements to a temp array and then copy them
    //back to the correct location
}