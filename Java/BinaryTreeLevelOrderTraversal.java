/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
public class BinaryTreeLevelOrderTraversal {

    // Iterative solution. Best. O(n) time and O(n) space
    public List<List<Integer>> levelOrder0(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        Deque<TreeNode> queue = new ArrayDeque<>();
        queue.offer(root);
        while(!queue.isEmpty()) {
            int size = queue.size();
            List<Integer> levelValues = new ArrayList<>();
            for (int i = 0; i < size; ++i) {
                TreeNode cur = queue.poll();
                levelValues.add(cur.val);
                if (cur.left != null) {
                    queue.offer(cur.left);
                }
                if (cur.right != null) {
                    queue.offer(cur.right);
                }
            }
            result.add(levelValues);
        }
        return result;
    }
    
    // Recursive solution. O(n) time and O(n) space
    private void levelOrderRecursive(TreeNode root, int level, List<List<Integer>> result) {
        if (root != null) {
            if (result.size() <= level) {
                result.add(new ArrayList<>());
            }
            List<Integer> levelValues = result.get(level);
            levelValues.add(root.val);
            levelOrderRecursive(root.left, level+1, result);
            levelOrderRecursive(root.right, level+1, result);
        }
    }
    
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        levelOrderRecursive(root, 0, result);
        return result;
    }
}