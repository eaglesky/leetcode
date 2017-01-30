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

    //When the slow pointer and the fast pointer first meet,
    //let the distance that the slow pointer has gone through be h,
    //then the distance that the fast pointer has gone through is 2h,
    //and let the circumference be c, then 2h - h = kc, k is a positive
    //integer. So h = kc. So if one slow pointer starts again
    //from the origin, and another slow pointer starts from the meeting
    //point, they should finally meet at the meeting point. And the 
    //first point they meed is where the cycle begins.
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