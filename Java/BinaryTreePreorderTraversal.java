/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
public class BinaryTreePreorderTraversal {
    
    private void preorderTraversalRecursive(TreeNode root, List<Integer> result) {
        if (root != null) {
            result.add(root.val);
            preorderTraversalRecursive(root.left, result);
            preorderTraversalRecursive(root.right, result);
        }
    }
    
    // Recursive solution.
    public List<Integer> preorderTraversal0(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        preorderTraversalRecursive(root, result);
        return result;
    }
    
    // Iterative solution 1, inferred from the solution of inorder traversal
    public List<Integer> preorderTraversal1(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        Deque<TreeNode> stack = new ArrayDeque<>();
        for(TreeNode cur = root; (cur != null) || (!stack.isEmpty());) {
            if (cur != null) {
                result.add(cur.val);
                stack.push(cur);
                cur = cur.left;
            } else {
                cur = stack.pop();
                cur = cur.right;
            }
        }
        return result;
    }
    
    // Iterative sotuion 2, easy to be generalized to N-ary tree
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        Deque<TreeNode> stack = new ArrayDeque<>();
        // pushing a null element to Deque will throw an exception
        if (root == null) {
            return result;
        }
        stack.push(root);
        while(!stack.isEmpty()) {
            TreeNode cur = stack.pop();
            result.add(cur.val);
            if (cur.right != null) {
                stack.push(cur.right);
            }
            if (cur.left != null) {
                stack.push(cur.left);
            }
        }
        return result;
    }

    // Morris traversal. O(n) time and O(1) space
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        for(TreeNode cur = root; cur != null;) {
            if (cur.left == null) {
                result.add(cur.val);
                cur = cur.right;
            } else {
                TreeNode next = cur.left;
                for(; (next.right != null) && (next.right != cur); next = next.right);
                if (next.right == null) {
                    next.right = cur;
                    result.add(cur.val);
                    cur = cur.left;
                } else {
                    next.right = null;
                    cur = cur.right;
                }
            }
        }
        return result;
    }
}