/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
public class ReorderList {
    
    private ListNode reverseLinkedList(ListNode head) {
        ListNode newHead = null;
        for (ListNode cur = head; cur != null;) {
            ListNode next = cur.next;
            cur.next = newHead;
            newHead = cur;
            cur = next;
        }
        return newHead;
    }
    
    public void reorderList(ListNode head) {
        if (head == null) {
            return;
        }
        ListNode slow = head;
        ListNode fast = head;
        for (; fast.next != null && fast.next.next != null; slow = slow.next, fast = fast.next.next);
        ListNode head2 = slow.next;
        slow.next = null;
        head2 = reverseLinkedList(head2);
        ListNode cur = head;
        for (ListNode cur2 = head2; cur2 != null; ) {
            ListNode next2 = cur2.next;
            cur2.next = cur.next;
            cur.next = cur2;
            cur2 = next2;
            cur = cur.next.next;
        }
        return;
    }
}