/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
public class FlattenBinaryTreeToLinkedList {
    
    // Recursive solution
    // O(n) time and O(n) space
    private static TreeNode flattenRecursive(TreeNode root) {
        if (root == null) {
            return null;
        }
        TreeNode leftTail = flattenRecursive(root.left);
        TreeNode rightTail = flattenRecursive(root.right);
        if (leftTail != null) {
            leftTail.right = root.right;
            root.right = root.left;
        }
        root.left = null;
        rightTail = (rightTail == null) ? leftTail : rightTail;
        return (root.right == null) ? root : rightTail;
    }
    
    public void flatten0(TreeNode root) {
        flattenRecursive(root);
    }
    
    //Iterative solution using stack
    public void flatten1(TreeNode root) {
        if (root == null) {
            return;
        }
        Deque<TreeNode> stack = new ArrayDeque<>();
        stack.push(root);
        TreeNode pre = null;
        while(!stack.isEmpty()) {
            TreeNode cur = stack.pop();
            if (pre != null) {
                pre.right = cur;
                pre.left = null;
            }
            pre = cur;
            if (cur.right != null) {
                stack.push(cur.right);
            }
            if (cur.left != null) {
                stack.push(cur.left);
            }
        }
    }
    
    //Iterative solution
    //O(n) time and O(1) space
    public void flatten(TreeNode root) {
        for(TreeNode cur = root; cur != null; cur = cur.right) {
            if (cur.left != null) {
                if (cur.right != null) {
                    TreeNode next = cur.left;
                    for(; next.right != null; next = next.right);
                    next.right = cur.right;               
                }
                cur.right = cur.left;
                cur.left = null;
            }
        }
    }
    
}