/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
public class RemoveNthNodeFromEndLinkedList{
    public ListNode removeNthFromEnd(ListNode head, int n) {
        if (n <= 0) {
            return null;
        }
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode p1 = dummy;
        int i = 0;
        for (; p1.next != null && i < n;++i, p1 = p1.next);
        if (i < n) {
            return null;
        }
        ListNode p2 = dummy;
        for (; p1.next != null; p1 = p1.next, p2 = p2.next);
        p2.next = p2.next.next;
        return dummy.next;
    }
}