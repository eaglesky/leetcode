/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
public class BinaryTreePostorderTraversal {
    
    private static void postorderTraversalRecursive(TreeNode root, List<Integer> result) {
        if (root != null) {
            postorderTraversalRecursive(root.left, result);
            postorderTraversalRecursive(root.right, result);
            result.add(root.val);
        }
    }
    
    // Recursive solution
    public List<Integer> postorderTraversal0(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        postorderTraversalRecursive(root, result);
        return result;
    }
    
    // Iterative solution with stack first trial
    public List<Integer> postorderTraversal1(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        Deque<TreeNode> stack = new ArrayDeque<>();
        
        TreeNode pre = new TreeNode(-1);
        for(TreeNode cur = root; (cur != null);) {

            if (cur != stack.peek()) {
                pre = cur;
                stack.push(cur);
                if (cur.left != null) {
                    cur = cur.left;
                } else if (cur.right != null) {
                    cur = cur.right;
                } else {
                    result.add(cur.val);
                    stack.pop();
                    cur = stack.peek();
                }
            } else {
                if (cur.right == pre || cur.right == null) {
                    pre = cur;
                    result.add(cur.val);
                    stack.pop();
                    cur = stack.peek();
                } else {
                    cur = cur.right;
                }
            }
        }
        return result;
    }
    
    // Best iterative solution with stack
    // When cur is null, there is no hurry to move it. Keeping it as null is a good idea!
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        Deque<TreeNode> stack = new ArrayDeque<>();
        
        TreeNode pre = null;
        for (TreeNode cur = root; (cur != null) || (!stack.isEmpty());) {
            if (cur != null) {
                stack.push(cur);
                cur = cur.left;
            } else {
                TreeNode top = stack.peek();
                if (top.right != null && (top.right != pre)) {
                    cur = top.right;
                } else {
                    pre = stack.pop();
                    result.add(pre.val);
                }
            }
        }
        return result;
    }
}