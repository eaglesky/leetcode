/**
 * Definition for binary tree with next pointer.
 * public class TreeLinkNode {
 *     int val;
 *     TreeLinkNode left, right, next;
 *     TreeLinkNode(int x) { val = x; }
 * }
 */
public class PopNextRight2 {
    public void connect(TreeLinkNode root) {
        for(TreeLinkNode cur = root; cur != null;) {
            TreeLinkNode pre = null;
            TreeLinkNode nextCur = null;
            for(; cur != null; cur = cur.next) {
                TreeLinkNode firstNonNullNode = null;
                if (cur.left != null) {
                    firstNonNullNode = cur.left;
                    cur.left.next = cur.right;
                } else if (cur.right != null) {
                    firstNonNullNode = cur.right;
                }
                if (pre != null) {
                    pre.next = firstNonNullNode;
                }
                if (nextCur == null) {
                    nextCur = firstNonNullNode;
                }
                pre = (cur.right != null) ? cur.right : ((cur.left != null) ? cur.left : pre);
            }
            cur = nextCur;
        }
    }

    //Second try
    public void connect(TreeLinkNode root) {
        for (TreeLinkNode curNode = root; curNode != null;) {
            TreeLinkNode nextStartNode = null;
            TreeLinkNode preNode = null;
            for (;curNode != null; curNode = curNode.next) {
                if (curNode.left != null && curNode.right != null) {
                    curNode.left.next = curNode.right;
                } 
                if (preNode != null && (curNode.left != null || curNode.right != null)) {
                    preNode.next = (curNode.left != null) ? curNode.left : curNode.right;
                }
                if (curNode.left != null || curNode.right != null) {
                    preNode = (curNode.right != null) ? curNode.right : curNode.left;
                }
                if (nextStartNode == null) {
                    nextStartNode = (curNode.left != null) ? curNode.left : curNode.right;
                }
            }
            curNode = nextStartNode;
        }
    }
}