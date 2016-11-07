/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
public class BinaryTreeRightSideView {
    
    //Iterative solution
    public List<Integer> rightSideView0(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        Deque<TreeNode> queue = new ArrayDeque<>();
        queue.offer(root);
        while(!queue.isEmpty()) {
            TreeNode node = null;
            int size = queue.size();
            for(int i = 0; i < size; ++i) {
                node = queue.poll();
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
            result.add(node.val);
        }
        return result;
    }
    
    // Recursive solution
    private static void rightSideViewRecursive(TreeNode root, int level, List<Integer> result) {
        if (root != null) {
            if (level >= result.size()) {
                result.add(root.val);
            }
            rightSideViewRecursive(root.right, level+1, result);
            rightSideViewRecursive(root.left, level+1, result);
        }
    }
    
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        rightSideViewRecursive(root, 0, result);
        return result;
    }
}