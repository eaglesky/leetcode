/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
public class BalancedBinaryTree {
    
    private static boolean isBalancedRecursive0(TreeNode root, int[] height) {
        if (root == null) {
            height[0] = 0;
            return true;
        }    
        int[] leftHeight = new int[]{0};
        int[] rightHeight = new int[]{0};
        boolean leftIsBalanced = isBalancedRecursive0(root.left, leftHeight);
        boolean rightIsBalanced = isBalancedRecursive0(root.right, rightHeight);
        height[0] = Math.max(leftHeight[0], rightHeight[0]) + 1;
        if (!leftIsBalanced || !rightIsBalanced) {
            return false;
        } else {
            return Math.abs(leftHeight[0] - rightHeight[0]) <= 1;
        }
    }
    
    public boolean isBalanced0(TreeNode root) {
        return isBalancedRecursive0(root, new int[]{0});
    }
    
    //Improved solution
    private static int getHeightOfBalancedTree(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int leftHeight = getHeightOfBalancedTree(root.left);
        int rightHeight = getHeightOfBalancedTree(root.right);
        if (leftHeight == -1 || rightHeight == -1 || Math.abs(leftHeight - rightHeight) > 1) {
            return -1;
        }
        return Math.max(leftHeight, rightHeight) + 1;
    }
    
    public boolean isBalanced(TreeNode root) {
        return (getHeightOfBalancedTree(root) != -1);
    }
}