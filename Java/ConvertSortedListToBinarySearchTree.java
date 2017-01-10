/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
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
    
    // Top-down approach, O(nlogn) time and O(logn) space
    private TreeNode createBSTRecursive(ListNode head, ListNode end) {
        if (head == null) {
            return null;
        }
        ListNode slow = head, fast = head;
        for(; fast.next != end && fast.next.next != end; slow = slow.next, fast = fast.next.next);
        TreeNode root = new TreeNode(slow.val);
        if (head != slow) {
            root.left = createBSTRecursive(head, slow);
        }
        if (slow.next != end) {
            root.right = createBSTRecursive(slow.next, end);
        }
        return root;
    }
    
    public TreeNode sortedListToBST0(ListNode head) {
        return createBSTRecursive(head, null);
    }
    
    // Bottom-up approach, O(n) time and O(logn) space
    private TreeNode createBSTBottomUp(ListNode[] head, int len) {
        if (len == 0) {
            return null;
        }
        TreeNode leftNode = createBSTBottomUp(head, len / 2);
        TreeNode root = new TreeNode(head[0].val);
        root.left = leftNode;
        head[0] = head[0].next;
        root.right = createBSTBottomUp(head, len - len/2 - 1);         

        return root;
    }
    
    public TreeNode sortedListToBST(ListNode head) {
        int len = 0;
        for(ListNode cur = head; cur != null; cur = cur.next, len++);
        
        return createBSTBottomUp(new ListNode[]{head}, len);
    }
}