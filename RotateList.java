/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
public class RotateList {
    public ListNode rotateRight(ListNode head, int k) {
        if (k <= 0 || head == null) {
            return head;
        }
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode pre = dummy;
        ListNode cur = dummy;
        int len = 0;
        for(; cur.next != null; cur = cur.next, ++len);
        k %= len;
        if (k == 0) {
            return head;
        }
        cur.next = head;
        for(int i = 0; i < len - k; pre = pre.next, ++i);
        head = pre.next;
        pre.next = null;
        return head;
    }
}