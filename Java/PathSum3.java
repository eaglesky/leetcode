/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
public class PathSum3 {
    

    private void addSumCounts(int curVal, Map<Integer, Integer> sumCounts, Map<Integer, Integer> subSumCounts) {
        for (Map.Entry<Integer, Integer> entry : subSumCounts.entrySet()) {
            int newSum = entry.getKey() + curVal;
            int newCount = sumCounts.getOrDefault(newSum, 0) + entry.getValue();
            sumCounts.put(newSum, newCount);
        }
    }
    
    private Map<Integer, Integer> getSumCounts(TreeNode root, int[] count, int target) {
        Map<Integer, Integer> sumCounts = new LinkedHashMap<>();
        if (root == null) {
            return sumCounts;
        }
        sumCounts.put(root.val, 1);
        Map<Integer, Integer> leftSumCounts = getSumCounts(root.left, count, target);
        Map<Integer, Integer> rightSumCounts = getSumCounts(root.right, count, target);
        addSumCounts(root.val, sumCounts, leftSumCounts);
        addSumCounts(root.val, sumCounts, rightSumCounts);
        count[0] += sumCounts.getOrDefault(target, 0);
        return sumCounts;
    }
    
    //Bottom-up solution. O(n^2) time.
    public int pathSum0(TreeNode root, int sum) {
        int[] count = new int[1];
        getSumCounts(root, count, sum);
        return count[0];
    }
    

    //Top-down solution. Best. O(n) time and O(n) space.
    private int dfs(TreeNode cur, int curSum, Map<Integer, Integer> prefixSumCounts, int target) {
        int result = 0;
        if (cur == null) {
            return result;
        }
        curSum += cur.val;
        result += prefixSumCounts.getOrDefault(curSum - target, 0);
        prefixSumCounts.put(curSum, prefixSumCounts.getOrDefault(curSum, 0) + 1);
        result += dfs(cur.left, curSum, prefixSumCounts, target);
        result += dfs(cur.right, curSum, prefixSumCounts, target);
        prefixSumCounts.put(curSum, prefixSumCounts.get(curSum) - 1);
        return result;
    }
    
    public int pathSum(TreeNode root, int sum) {
        Map<Integer, Integer> prefixSumCounts = new HashMap<>();
        prefixSumCounts.put(0, 1);
        return dfs(root, 0, prefixSumCounts, sum);
    }
}