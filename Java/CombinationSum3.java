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
}