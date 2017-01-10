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
}