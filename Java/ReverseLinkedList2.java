/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
public class ReverseLinkedList2 {

	// Takes care of general case(igorning 1 <= m <= n <= L)
    public ListNode reverseBetween(ListNode head, int m, int n) {
        if (n - m <= 0) {
            return head;
        }
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode pre = dummy;
        for (int i = 0; i < m-1; ++i, pre = pre.next);
        ListNode curTail = pre.next;
        if (curTail == null) {
            return head;
        }
        for (int i = 0; (curTail.next != null) && (i < n-m); ++i) {
            ListNode cur = curTail.next;
            curTail.next = cur.next;
            cur.next = pre.next;
            pre.next = cur;
        }
        return dummy.next;
    }

    // Another implementation
    // Insert into a temporary sublist first and then insert it back.
    // Seems more robust
    public ListNode reverseBetween(ListNode head, int m, int n) {
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode p = dummy;
        int i = 0;
        for(; p != null && i < m - 1; ++i, p = p.next);
        ListNode prev = p;
        p = p.next;
        ListNode newHead = null;
        for(++i; i <= n; ++i) {
            ListNode next = p.next;
            p.next = newHead;
            newHead = p;
            p = next;
        }
        prev.next.next = p;
        prev.next = newHead;
        return dummy.next;
    }
}