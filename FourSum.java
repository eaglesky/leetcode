public class FourSum {
    
    // O(n^3) time and O(n^2) space
    public List<List<Integer>> fourSum(int[] nums, int target) {
        Set<List<Integer>> solutionSet = new HashSet<>();
        Arrays.sort(nums);
        Map<Integer, List<int[]>> sumToPairs = new HashMap<>();
        for (int i = 0; i < nums.length; ++i) {
            for (int j = i + 1; j < nums.length; ++j) {
                List<int[]> prevIdList = sumToPairs.get(target - nums[i]-nums[j]);
                if (prevIdList != null) {
                    for (int[] prevIds : prevIdList) {
                        List<Integer> solution = new ArrayList<>();
                        solution.add(prevIds[0]);
                        solution.add(prevIds[1]);
                        solution.add(nums[i]);
                        solution.add(nums[j]);
                        solutionSet.add(solution);
                    }
                }
            }
            for (int j = 0; j < i; ++j) {
                int curSum = nums[j] + nums[i];
                List<int[]> prevIdList = sumToPairs.get(curSum);
                if (prevIdList == null) {
                    prevIdList = new ArrayList<>();
                    sumToPairs.put(curSum, prevIdList);
                }
                prevIdList.add(new int[]{nums[j], nums[i]});
            }
        }
        return new ArrayList<List<Integer>>(solutionSet);
    }
}