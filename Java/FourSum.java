public class FourSum {
    
    // O(n^3) time and O(1) space
    public List<List<Integer>> fourSum(int[] nums, int target) {
        Arrays.sort(nums);
        List<List<Integer>> results = new ArrayList<>();
        for (int i = 0; i < nums.length - 3; ++i) {
            // Can add check: if (nums[i]*4 > target) break;
            if ((i > 0) && (nums[i] == nums[i-1])) {
                continue;
            }
            for (int j = i+1; j < nums.length - 2; ++j) {
                // Can add check: if (nums[j]*3 > target - nums[i]) return results;
                if ((j > i+1) && (nums[j] == nums[j-1])) {
                    continue;
                }
                int other = target - nums[i] - nums[j];
                int start = j + 1;
                int end = nums.length - 1;
                while (start < end) {
                    if ((start > j+1) && (nums[start] == nums[start-1])) {
                        start++;
                        continue;
                    }
                    if ((end < nums.length-1) && (nums[end] == nums[end+1])) {
                        end--;
                        continue;
                    }
                    if (nums[start] + nums[end] == other) {
                        List<Integer> result = new ArrayList<>();
                        result.add(nums[i]);
                        result.add(nums[j]);
                        result.add(nums[start]);
                        result.add(nums[end]);
                        results.add(result);
                        end--;
                    } else if (nums[start] + nums[end] < other) {
                        start++;
                    } else {
                        end--;
                    }
                }
            }
        }
        return results;
    }

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