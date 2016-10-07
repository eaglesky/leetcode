/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
public class ReverseLinkedList {

    // Iterative solution
    public ListNode reverseList0(ListNode head) {
        ListNode newHead = null;
        for(; head != null; ) {
            ListNode next = head.next;
            head.next = newHead;
            newHead = head;
            head = next;
        }
        return newHead;
    }

    // Recursive solution
    public ListNode reverseList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode next = head.next;
        ListNode reversedRestList = reverseList(next);
        next.next = head;
        head.next = null;
        return reversedRestList;
    }
}