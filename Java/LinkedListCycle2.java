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
    //first point they meet is where the cycle begins.

    //Better thought: let d0 be the distance from the head to the cycle starting
    //point. x be the distance from the cycle starting point to the first meeting
    //point of slow and fast pointers. Then 2*(d0 + x) = d0 + x + k*c, k is a
    //positive integer. Then we have d0 = k*c - x = (c - x) + k'*c, where k' is
    //an non-negative integer. c is the length of the cycle, which is always a
    //positive integer if exists. That's why we can let two pointers starting from
    //head and the meeting point move inwards to find the cycle starting point.
    //Adding a dummy can make it easier to deal with the case when the cycle starting
    //poing is the head.
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