/**
 * Definition for singly-linked list.
 * class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }
 */
public class LinkedListCycle2 {
    public ListNode detectCycle(ListNode head) {
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode slow = dummy;
        ListNode fast = dummy;
        for(; fast != null && fast.next != null && (fast == dummy || (fast != slow));
            slow = slow.next, fast = fast.next.next);
        if (fast == null || fast.next == null) {
            return null;
        }
        for(slow = dummy; slow != fast; slow = slow.next, fast = fast.next);
        return slow;
    }
}