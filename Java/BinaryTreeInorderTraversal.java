/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
public class BinaryTreeInorderTraversal {
    
    private void inorderTraversalRecursive(TreeNode root, List<Integer> result) {
        if (root != null) {
            inorderTraversalRecursive(root.left, result);
            result.add(root.val);
            inorderTraversalRecursive(root.right, result);
        }
    }
    
    // Recursive solution. O(n) time and O(n) space
    public List<Integer> inorderTraversal0(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        inorderTraversalRecursive(root, result);
        return result;
    }
    
    // Iterative solution first trial, O(n) time and O(n) space
    public List<Integer> inorderTraversal1(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        Deque<TreeNode> stack = new ArrayDeque<>();
        for(TreeNode cur = root; cur != null;) {
            while ((cur != stack.peek()) && (cur.left != null)) {
                stack.push(cur);
                cur = cur.left;
            }
            result.add(cur.val);
            if (cur == stack.peek()) {
                stack.pop();
            }
            if (cur.right != null) {
                cur = cur.right;
            } else {
                cur = stack.peek();
            }
        }
        return result;
    }
    
    // Iterative solution better version, O(n) time and O(n) space
    // At the beginning of each iteration, the recent node in the
    // stack is always the closest parent of cur node such that cur node
    // is on the left subtree of that parent. If cur is null, this means
    // the left tree has been visited and so we should visit the poped node.
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        Deque<TreeNode> stack = new ArrayDeque<>();
        for(TreeNode cur = root; cur != null || !stack.isEmpty();) {
            if (cur != null) {
                stack.push(cur);
                cur = cur.left;
            } else {
                cur = stack.pop();
                result.add(cur.val);
                cur = cur.right;
            }
        }
        return result;
    }

    // Morris traversal. O(n) time and O(1) space
    public List<Integer> inorderTraversal(TreeNode root) {
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
                    cur = cur.left;
                } else {
                    next.right = null;
                    result.add(cur.val);
                    cur = cur.right;
                }
            }
        }
        return result;
    }
}