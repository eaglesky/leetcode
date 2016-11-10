/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
public class RecoverBinarySearchTree {
    
    private static void detect(TreeNode pre, TreeNode cur, TreeNode[] swappedNodes) {
        if (pre != null && pre.val > cur.val) {
            swappedNodes[0] = (swappedNodes[0] == null) ? pre : swappedNodes[0];
            swappedNodes[1] = cur;
        }
    }
    
    //Morris traversal
    public void recoverTree(TreeNode root) {
        //Larger node and smaller node
        TreeNode[] swappedNodes = new TreeNode[]{null, null};
        TreeNode cur = root;
        TreeNode pre = null;
        while(cur != null) {
            if (cur.left != null) {
                TreeNode next = cur.left;
                for(; next.right != null && next.right != cur; next = next.right);
                if (next.right == null) {
                    next.right = cur;
                    cur = cur.left;
                } else {
                    next.right = null;
                    detect(pre, cur, swappedNodes);
                    pre = cur;
                    cur = cur.right;
                }
            } else {
                detect(pre, cur, swappedNodes);
                pre = cur;
                cur = cur.right;
            }
        }
        if (swappedNodes[0] != null && swappedNodes[1] != null) {
            int temp = swappedNodes[0].val;
            swappedNodes[0].val = swappedNodes[1].val;
            swappedNodes[1].val = temp;
        }
    }
}