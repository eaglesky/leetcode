/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
public class SwapNodesInPairs {
    public ListNode swapPairs(ListNode head) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode p = dummy;
        for (; p.next != null && p.next.next != null; p = p.next.next) {
            ListNode p2 = p.next.next;
            p.next.next = p2.next;
            p2.next = p.next;
            p.next = p2;
        }
        return dummy.next;
    }
}