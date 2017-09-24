/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class UniqueBinarySearchTrees2 {
    
    private List<TreeNode> dfs(int start, int end, Map<List<Integer>, List<TreeNode>> cache) {
        if (start <= 0 || end <= 0 || start > end) {
            List<TreeNode> nodes = new ArrayList<>();
            nodes.add(null);
            return nodes;
        }
        List<Integer> ids = Arrays.asList(start, end);
        List<TreeNode> nodes = cache.get(ids);
        if (nodes != null) {
            return nodes;
        }
        nodes = new ArrayList<>();
        for (int i = start; i <= end; ++i) {
            List<TreeNode> leftNodes = dfs(start, i - 1, cache);
            List<TreeNode> rightNodes = dfs(i + 1, end, cache);
            for (int l = 0; l < leftNodes.size(); ++l) {
                for (int r = 0; r < rightNodes.size(); ++r) {
                    TreeNode root = new TreeNode(i);
                    root.left = leftNodes.get(l);
                    root.right = rightNodes.get(r);
                    nodes.add(root);
                }
            }
        }
        cache.put(ids, nodes);
        return nodes;
    }
    
    public List<TreeNode> generateTrees(int n) {
        if (n <= 0) {
            return new ArrayList<TreeNode>();
        }
        return dfs(1, n, new HashMap<>());
    }
    
    //Another solution -- build BST for (1..n) based on the trees for (1...n-1).
    //https://discuss.leetcode.com/topic/6711/share-a-c-dp-solution-with-o-1-space
    //Not O(1) space
}