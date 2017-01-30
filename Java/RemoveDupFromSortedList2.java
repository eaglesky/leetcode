/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
public class RemoveDupFromSortedList2 {
    public ListNode deleteDuplicates0(ListNode head) {
        if (head == null) {
            return head;
        }
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode pre = dummy;
        ListNode cur = head;
        for (; cur != null;) {
            for (; (cur.next != null) && (cur.val == cur.next.val); cur = cur.next);
            if (pre.next != cur) {
                pre.next = cur.next;
            } else {
                pre = pre.next;
            }
            cur = cur.next;
        }
        return dummy.next;
    }

    //Second try
    public ListNode deleteDuplicates(ListNode head) {
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode prev = dummy;
        for(; prev.next != null;) {
            ListNode cur = prev.next;
            for(; cur.next != null && cur.next.val == cur.val; cur = cur.next);
            if (cur != prev.next) {
                prev.next = cur.next;
                cur.next = null;
            } else {
                prev = prev.next;
            }
        }
        return dummy.next;
    }
}