class FindAllDuplicatesInArray {
    public List<Integer> findDuplicates(int[] nums) {
        List<Integer> result = new ArrayList<>();
        for (int num : nums) {
            int id = Math.abs(num) - 1;
            if (nums[id] > 0) {
                nums[id] = -nums[id];
            } else {
                result.add(id + 1);
            }
        }
        return result;
    }
}