public class CombinationSum3 {
    
    private void dfs(int start, int id, int sum, Integer[] curCombination, List<List<Integer>> result) {
        int k = curCombination.length;
        if (id >= k) {
            if (sum == 0) {
                result.add(new ArrayList(Arrays.asList(curCombination)));           
            }
            return;
        }

        for (int i = start; i <= id + 10 - k; ++i) {
            curCombination[id] = i;
            dfs(i+1, id+1, sum-i, curCombination, result);   
        }
    }
    
    public List<List<Integer>> combinationSum3(int k, int n) {
        List<List<Integer>> result = new ArrayList<>();
        if (k <= 0 || k >= 10 || n < (1 + k) * k / 2 || n > (19 - k) * k / 2) {
            return result;
        }
        dfs(1, 0, n, new Integer[k], result);
        return result;
    }

    //Second try
    private static void dfs(int curId, Integer[] curSet, List<List<Integer>> allSets, int targetSum) {
        if (curId >= curSet.length) {
            if (targetSum == 0) {
                allSets.add(new ArrayList<Integer>(Arrays.asList(curSet)));
            }
            return;
        }
        int start = curId == 0 ? 1 : curSet[curId-1] + 1;
        for (int num = start; num <= 9 && (targetSum >= num); ++num) {
            curSet[curId] = num;
            dfs(curId + 1, curSet, allSets, targetSum - num);
        }
    }
    
    public List<List<Integer>> combinationSum3(int k, int n) {
        List<List<Integer>> result = new ArrayList<>();
        if (k <= 0 || n <= k) {
            return result;
        }
        dfs(0, new Integer[k], result, n);
        return result;
    }

    //Better in interview:
    private void dfs(int start, int k, int target, List<Integer> combination,
                List<List<Integer>> combinations) {
        if (target <= 0 || combination.size() >= k) {
            if (target == 0 && combination.size() == k) {
                combinations.add(new ArrayList<>(combination));
            }
            return;
        }
        //Pruning
        int minNum = Math.max(start, target - 9 * (k - combination.size() - 1));
        int maxNum = Math.min(9, target / (k - combination.size()));
        for (int num = minNum; num <= maxNum; ++num) {
            combination.add(num);
            dfs(num + 1, k, target - num, combination, combinations);
            combination.remove(combination.size()-1);
        }
    }
    
    public List<List<Integer>> combinationSum3(int k, int n) {
        List<List<Integer>> result = new ArrayList<>();
        if (k <= 0 || n <= 0) {
            return result;
        }
        dfs(1, k, n, new ArrayList<>(k), result);
        return result;
    }
}