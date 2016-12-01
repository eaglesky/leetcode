public class CombinationSum {
    
    private void dfs(int[] candidates, int start, int target, List<Integer> combination, List<List<Integer>> result) {
        if (target == 0) {
            result.add(new ArrayList<Integer>(combination));
            return;
        }
        for(int i = start; i < candidates.length; ++i) {
            if (candidates[i] <= target) {
                combination.add(candidates[i]);
                dfs(candidates, i, target - candidates[i], combination, result);
                combination.remove(combination.size()-1);
            }
        }
    }
    
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<>();
        if (candidates == null || candidates.length == 0) {
            return result;
        }
        dfs(candidates, 0, target, new ArrayList<Integer>(), result);
        return result;
    }

    //DP solution?
}