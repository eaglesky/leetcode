/**
 * Definition for binary tree with next pointer.
 * public class TreeLinkNode {
 *     int val;
 *     TreeLinkNode left, right, next;
 *     TreeLinkNode(int x) { val = x; }
 * }
 */
public class PopNextRight {
    public void connect(TreeLinkNode root) {
        for(TreeLinkNode cur = root; cur != null && cur.left != null;) {
            TreeLinkNode pre = null;
            TreeLinkNode nextCur = cur.left;
            for(; cur != null; cur = cur.next) {
                cur.left.next = cur.right;
                if (pre != null) {
                    pre.next = cur.left;
                }
                pre = cur.right;
            }
            cur = nextCur;
        }
    }
}