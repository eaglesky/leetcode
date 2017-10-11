class FindAllNumbersDisappearedInArray {
    
    //O(n) time and O(1) space
    //Can add extra loop to restore original array by assigning the absolute value
    public List<Integer> findDisappearedNumbers(int[] nums) {
        List<Integer> result = new ArrayList<>();
        for (int num : nums) {
            int id = Math.abs(num) - 1;
            if (nums[id] > 0) {
                nums[id] = -nums[id];
            }
        }
        for (int i = 0; i < nums.length; ++i) {
            if (nums[i] > 0) {
                result.add(i + 1);
            }
        }
        return result;
    }

    //Another method is similar to the one in finding first missing positive
}