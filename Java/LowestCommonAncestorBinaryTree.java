/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
public class LowestCommonAncestorBinaryTree {
    
    //Recursive solution. O(n) time and O(h) space
    private TreeNode findLCA(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || root == p || root == q) {
            return root;
        }
        TreeNode leftNode = findLCA(root.left, p, q);
        TreeNode rightNode = findLCA(root.right, p, q);
        if (leftNode != null && rightNode != null) {
            return root;
        } else if (leftNode != null) {
            return leftNode;
        } else {
            return rightNode;
        }
    }
    
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        return findLCA(root, p, q);
    }
    
    //Iterative solution has the same performance. But the memory overhead is higher.
    //https://discuss.leetcode.com/topic/27479/java-python-iterative-solution/2
    //The idea is to use iterative traversal to build a map of node to its parent.
    //Then traverse back from p to root and record all nodes in a set,
    //and traverse back from q to find out the first node that exists in the set,
    //which is the LCA.
}