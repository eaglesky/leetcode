/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
public class Solution {
    
    //Recursive solution. This essentially convert the original problem
    //into finding 
    public int minDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        if (root.right == null) {
            return minDepth(root.left) + 1;
        }
        if (root.left == null) {
            return minDepth(root.right) + 1;
        }
        return Math.min(minDepth(root.left), minDepth(root.right)) + 1;
    }
    
    //Iterative solution: level order traversal or iterative postorder traversal
}