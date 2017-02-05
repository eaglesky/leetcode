/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
public class LowestCommonAncestorBST {

	//O(h) time and O(1) space
	//The value of LCA x must satisfy: p.val <= x <= q.val, and it must be
	//the first node that satisfies this when traversing down from the root.
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (p == q) {
            return p;
        } else if (p == null) {
            return q;
        } else if (q == null) {
            return p;
        }
        int minValue = Math.min(p.val, q.val);
        int maxValue = Math.max(p.val, q.val);
        TreeNode cur = root;
        while (cur != null) {
            if (cur.val >= minValue && cur.val <= maxValue) {
                return cur;
            } else if (cur.val > maxValue) {
                cur = cur.left;
            } else {
                cur = cur.right;
            }
        }
        return cur;
    }
}