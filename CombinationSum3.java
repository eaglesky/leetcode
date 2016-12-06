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
}