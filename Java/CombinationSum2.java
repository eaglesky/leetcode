public class CombinationSum2 {
    
    private void dfs(int[] candidates, int start, int target, List<Integer> combination, List<List<Integer>> result) {
        if (target == 0) {
            result.add(new ArrayList<Integer>(combination));
            return;
        }
        for(int i = start; i < candidates.length; ++i) {
            if (i > start && candidates[i] == candidates[i-1]) {
                continue;
            }
            if (candidates[i] > target) {
                break;
            }
            combination.add(candidates[i]);
            dfs(candidates, i + 1, target - candidates[i], combination, result);
            combination.remove(combination.size()-1);
        }
    }
    
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<>();
        if (candidates == null || candidates.length == 0) {
            return result;
        }
        Arrays.sort(candidates);
        //Initialize array list with initial capacity if possible!
        dfs(candidates, 0, target, new ArrayList<Integer>(candidates.length), result);
        return result;
    }
}