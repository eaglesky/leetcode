/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class DiameterBinaryTree {
    
    private int getHeight(TreeNode root, int[] maxLen) {
        if (root == null) {
            return 0;
        }    
        int leftHeight = getHeight(root.left, maxLen);
        int rightHeight = getHeight(root.right, maxLen);
        maxLen[0] = Math.max(maxLen[0], leftHeight + rightHeight);
        return Math.max(leftHeight, rightHeight) + 1;
    }
    
    public int diameterOfBinaryTree(TreeNode root) {
        int[] maxLen = new int[1];
        getHeight(root, maxLen);
        return maxLen[0];
    }
}