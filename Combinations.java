public class Solution {
    
    //Standard dfs solution, O(k*Cnk) time and O(k) additional space
    private void dfsCombinations(int start, int n, int k, List<Integer> curCombination, List<List<Integer>> result) {
        int count = curCombination.size();
        if (count == k) {
            result.add(new ArrayList<Integer>(curCombination));
            return;
        }
        for (int num = start; num <= n + 1 - k + count; ++num) {
            curCombination.add(num);
            dfsCombinations(num+1, n, k, curCombination, result);
            curCombination.remove(curCombination.size()-1);
        }    
    }
    
    public List<List<Integer>> combine0(int n, int k) {
        List<List<Integer>> result = new ArrayList<>();
        if (n <= 0 || k <= 0 || k > n) {
            return result;
        }
        dfsCombinations(1, n, k, new ArrayList<Integer>(k), result);
        return result;
    }
    
    //Iterative solution based on induction:
    //To find out Cnk, find Cn(k-1) first.
    //Very inefficient and time out on OJ.
    public List<List<Integer>> combine1(int n, int k) {
        List<List<Integer>> result = new ArrayList<>();
        if (n <= 0 || k <= 0 || k > n) {
            return result;
        }
        List<List<Integer>> preCombinations = new ArrayList<>();
        preCombinations.add(new ArrayList<Integer>());
        for (int i = 0; i < k; ++i) {
            List<List<Integer>> curCombinations = new ArrayList<>();
            for (List<Integer> preCombination : preCombinations) {
                int num = (preCombination.isEmpty()) ? 1 : preCombination.get(preCombination.size()-1) + 1;
                for (; num <= n; ++num) {
                    List<Integer> curCombination = new ArrayList<>(preCombination);
                    curCombination.add(num);
                    curCombinations.add(curCombination);
                }
            }
            preCombinations = curCombinations;
        }
        return preCombinations;
    }
    
//Another iterative using permutation on bit vector takes O(n*Cnk) time and O(n) space(See the book)
}