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

    //Sort first is preferable.
    //Similar implementation to above. Might be easier to write the code
    private void dfs(int id, int[] candidates, int curSum, int target,
                     List<Integer> curSolution, List<List<Integer>> solutions) {
        if (curSum >= target) {
            if (curSum == target) {
                solutions.add(new ArrayList<>(curSolution));
            }
            return;
        }
        for (int i = id; i < candidates.length; ++i) {
            curSolution.add(candidates[i]);
            curSum += candidates[i];
            dfs(i, candidates, curSum, target, curSolution, solutions);
            curSolution.remove(curSolution.size() - 1);
            if (curSum > target) {
                break;
            }
            curSum -= candidates[i];
        }
    }
    
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(candidates);
        dfs(0, candidates, 0, target, new ArrayList<>(), result);
        return result;
    }

    //Best Implementation, no sorting needed.
    void dfs(int[] candidates, int start, int target, List<Integer> curSolution, List<List<Integer>> result) {
        if (target <= 0) {
            if (target == 0) {
                result.add(new ArrayList<>(curSolution));
            }
            return;
        }
        
        for (int i = start; i < candidates.length; ++i) {
            curSolution.add(candidates[i]);
            dfs(candidates, i, target - candidates[i], curSolution, result);
            curSolution.remove(curSolution.size() - 1);
        }
    }
    
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<>();
        dfs(candidates, 0, target, new ArrayList<>(), result);
        return result;
    }
}