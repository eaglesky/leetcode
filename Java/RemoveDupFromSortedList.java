/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
public class RemoveDupFromSortedList {
    public ListNode deleteDuplicates0(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode pre = head;
        ListNode cur = head.next;
        for (; cur != null;) {
            if (cur.val == pre.val) {
                cur = cur.next;
                pre.next.next = null;
                pre.next = cur;
            } else {
                pre = pre.next;
                cur = cur.next;
            }
        }
        return head;
    }

    //Best solution. Can be generalized to RemoveDupFromSortedList III.
    public ListNode deleteDuplicates(ListNode head) {
        ListNode p = head;
        for(; p != null;) {
            if (p.next != null && p.next.val == p.val) {
                p.next = p.next.next;
            } else {
                p = p.next;
            }
        }
        return head;
    }
}