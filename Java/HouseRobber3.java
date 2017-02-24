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
    
    private static int[] robRecur(TreeNode root) {
        if (root == null) {
            return new int[]{0, 0};
        }    
        int[] curMax = new int[2];
        int[] leftMax = robRecur(root.left);
        int[] rightMax = robRecur(root.right);
        curMax[1] = leftMax[0] + rightMax[0] + root.val;
        curMax[0] = Math.max(leftMax[0], leftMax[1]) + Math.max(rightMax[0], rightMax[1]);
        return curMax;
    }
    
    //O(n) time and O(h) space
    public int rob(TreeNode root) {
        int[] curMax = robRecur(root);
        return Math.max(curMax[0], curMax[1]);
    }
}