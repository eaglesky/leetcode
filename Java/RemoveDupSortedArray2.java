public class RemoveDupSortedArray2 {

    // First try
    public int removeDuplicates(int[] nums) {
        int count = 1;
        int i = 0;
        for (int j = 1; j < nums.length; ++j) {
            if (nums[j] == nums[j-1]) {
                if (count < 2) {
                    count++;
                    nums[++i] = nums[j];
                }
            } else {
                nums[++i] = nums[j];
                count = 1;
            }
        }
        return (nums.length == 0) ? 0 : i+1;
    }

    // Another easy-to-come-up solution:
    public int removeDuplicates(int[] nums) {
        int k = 2;
        int lastId = 0;
        int dupCount = 0;
        for (int i = 1; i < nums.length; ++i) {
            if (nums[i] == nums[i-1]) {
                dupCount++;
            } else {
                dupCount = 0;
            }
            if (dupCount < k) {
                nums[++lastId] = nums[i];
            } 
        }
        return (nums.length == 0) ? 0 : lastId + 1;
    }

    // Or better:
    // This is the one to be used in interview.
    // Works as if copying the elements to a new array.
    // id always point to the position to be inserted,
    // and elements before id are de-duped.
    // nums[i-1] must be equal to the original element,
    // whether i and id are in sync or out of sync.
    // So count must be always correct, and thus
    // the last assignment also works.
    public int removeDuplicates(int[] nums) {
        int k = 2;
        int count = 1;
        int id = 0;
        for (int i = 0; i < nums.length; ++i) {
            if (i == 0 || nums[i] > nums[i-1]) {
                count = 1;
            } else {
                count++;
            }
            if (count <= k) {
                nums[id++] = nums[i];
            }
        }
        return id;
    }

    // Simpler solution:
    // Just imagine as if you are copying the elements to a new array
    // Compare the element in the original array with one in the "new array"
    // Very tricky! And very hard to come up with.
    public int removeDuplicates(int[] nums) {
        int i = 0;
        for (int num : nums) {
            if (i < 2 || (num != nums[i-2])) {
                nums[i++] = num;
            }
        }
        return i;
    }
}