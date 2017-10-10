public class FirstMissingPos {
    
    private boolean isValid(int a, int max) {
        return (a <= max) && (a > 0);
    }
    
    private void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
    
    //This is O(n) since each element will be checked against the destination
    //for at most once. 
    public int firstMissingPositive(int[] nums) {
        int max = nums.length;
        for (int i = 0; i < max; ++i) {
            for (; isValid(nums[i], max) && nums[nums[i]-1]!= nums[i]; ) {
                swap(nums, i, nums[i]-1);
            }
        }
        for (int i = 0; i < max; ++i) {
            if (nums[i] != i+1) {
                return i+1;
            }
        }
        return max+1;
    }

    //Second try. The first one is easier to write since swap can make 
    //the end condition check simpler.
    public int firstMissingPositive(int[] nums) {
        for (int i = 0; i < nums.length; ++i) {
            int num = nums[i];
            while(num >= 1 && num <= nums.length && (nums[num-1] != num)) {
                int newNum = nums[num - 1];
                nums[num - 1] = num;
                num = newNum;
            }
        }
        for (int i = 0; i < nums.length; ++i) {
            if (nums[i] != i + 1) {
                return i + 1;
            }
        }
        return nums.length + 1;
    }

    //Third try.
    //To find out the first missing positive, we only need to maintain an array
    //of same length as the input array, 0 ... n-1, representing if 1 ... n
    //is included or not. Only when the input nums are unique and they are
    //in the range of [1, n] can the first missing positive be n + 1, which is 
    //the largest possible one. If any one of them repeats the other nums, or
    //out of range(< 1 or > n), then the first missing posivie must be smaller than n.
    //We can use the input array to store nums such that if nums[i] == i + 1, then
    //i + 1 is included. 
    public int firstMissingPositive(int[] nums) {
        for (int i = 0; i < nums.length; ++i) {
            int curNum = nums[i];
            int id = i;
            for (; curNum != id + 1;) {
                if (curNum < 1 || curNum > nums.length) {
                    break;
                }
                id = curNum - 1;
                int temp = nums[id];
                nums[id] = curNum;
                curNum = temp;
            }
        }
        for (int i = 0; i < nums.length; ++i) {
            if (nums[i] != i + 1) {
                return i + 1;
            }
        }
        return nums.length + 1;
    }
}