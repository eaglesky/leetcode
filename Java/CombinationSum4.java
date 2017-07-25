import java.util.*;

public class CombinationSum4 {
    
    private static void findCombinations_0(int[] nums, int target, int start, List<Integer> curCombination, List<List<Integer>> combinations) {
        if (target == 0) {
            combinations.add(new ArrayList<>(curCombination));
            return;
        }
        
        for (int i = start; i < nums.length && nums[i] <= target; ++i) {
            curCombination.add(nums[i]);
            findCombinations_0(nums, target - nums[i], i, curCombination, combinations);
            curCombination.remove(curCombination.size()-1);
        } 
    }
    
    private static void swap(Integer[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
    
    private static void findPermutations(Integer[] nums, int start, List<List<Integer>> permutations) {
        if (start >= nums.length) {
            permutations.add(new ArrayList<>(Arrays.asList(nums)));
            return;
        }
        
        Set<Integer> visited = new HashSet<>();
        for (int i = start; i < nums.length; ++i) {
            if (visited.contains(nums[i])) {
                continue;
            }
            swap(nums, i, start);
            visited.add(nums[start]);
            findPermutations(nums, start+1, permutations);
            swap(nums, i, start);
        }    
    }
    
    //This solution is just for practice. TLE on [4,2,1], 32
    public int combinationSum4_0(int[] nums, int target) {
        Arrays.sort(nums);
        List<List<Integer>> combinations = new ArrayList<>();
        findCombinations(nums, target, 0, new ArrayList<>(), combinations);
        int count = 0;
        for (List<Integer> combination : combinations) {
        	//System.out.println("Combination = " + combination);
            List<List<Integer>> permutations = new ArrayList<>();
            findPermutations(combination.toArray(new Integer[0]), 0, permutations);
            count += permutations.size();
            //System.out.println(permutations.size());
            for (List<Integer> permutation : permutations) {
            	//System.out.println(permutation);
            }
        }
        return count;
    }

    //Better backtracking solution, still TLE on [4,2,1], 32
    //O(n^target) time and O(target) space
    private static void findCombinations(int[] nums, int start, int target, List<Integer> curCombination, List<List<Integer>> combinations) {
        if (target == 0) {
            combinations.add(new ArrayList<>(curCombination));
            return;
        }
        for (int i = start; i < nums.length; ++i) {
            swap(nums, start, i);
            if (nums[start] <= target) {
                curCombination.add(nums[start]);
                findCombinations(nums, start, target - nums[start], curCombination, combinations);
                curCombination.remove(curCombination.size()-1);
            }
            swap(nums, start, i);
        }
    }
    
    public int combinationSum4(int[] nums, int target) {
        List<List<Integer>> result = new ArrayList<>();
        findCombinations(nums, 0, target, new ArrayList<>(), result);
        return result.size();
    }

    private static void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    //Another good backtracking solution similar to CombinationSum I.
    //still TLE on [4,2,1], 32
    private void dfs(int[] candidates, int target,
                     List<Integer> curSolution, List<List<Integer>> solutions) {
        if (target <= 0) {
            if (target == 0) {
                solutions.add(new ArrayList<>(curSolution));
            }
            return;
        }
        for (int i = 0; i < candidates.length && candidates[i] <= target; ++i) {
            curSolution.add(candidates[i]);
            dfs(candidates, target - candidates[i], curSolution, solutions);
            curSolution.remove(curSolution.size() - 1);
        }
    }
    
    public int combinationSum4(int[] nums, int target) {
        Arrays.sort(nums);
        List<List<Integer>> result = new ArrayList<>();
        dfs(nums, target, new ArrayList<>(), result);
        return result.size();
    }

    //DP solution, O(n*target) time and O(target) space
    //https://discuss.leetcode.com/topic/52302/1ms-java-dp-solution-with-detailed-explanation
    //Let d[k] be the number of valid combinations whose sum is k.
    //d[k] = d[k - nums[0]] + d[k - nums[1]] + ... + d[k - nums[j]]
    //(nums[j] <= k, assuming nums is already sorted)
    public int combinationSum4(int[] nums, int target) {
        int[] count = new int[target + 1];
        count[0] = 1;
        Arrays.sort(nums);
        for (int curTarget = 0; curTarget < count.length; ++curTarget) {
            for (int i = 0; i < nums.length && nums[i] <= curTarget; ++i) {
                count[curTarget] += count[curTarget - nums[i]];
            }
        }
        return count[target];
    }

    //Using memoization can avoid unnecessary computing while using some recursion space
    //https://discuss.leetcode.com/topic/52255/java-recursion-solution-using-hashmap-as-memory

    //Follow-up: If negatives are allowed, then the length of combintation could be unlimited.
    //E.g. nums = [-1, 1], target = 1. Then combintation could be [-1, 1, 1], or [-1, -1, 1, 1, 1],
    //or [-1, -1, -1, 1, 1, 1, 1] ... . So there must be a constraint on the length of combinations.
    //Say the length of each combination is no greater than k, then we can use Memoization to
    //solve it. The cache should be (length_of_combination, target) -> number_of_combinations.
    //https://discuss.leetcode.com/topic/52290/java-follow-up-using-recursion-and-memorization
    //A stronger constraint could be each element cannot be used more than once. But memoization
    //would be harder to implement in such case.

    public static void main(String[] args) {
    	int[] nums = new int[]{1, 5, 8};
    	int target = 24;
    	int count = new CombinationSum4().combinationSum4(nums, target);
    	System.out.println("Count = " + count); //Should be 982
    }
}