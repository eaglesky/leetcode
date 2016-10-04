/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }
 */
public class IntersectionOfTwoLinkedLists {

    // First trial
    public ListNode getIntersectionNode0(ListNode headA, ListNode headB) {
        ListNode p1 = headA;
        ListNode p2 = headB;
        for (; (p1 != null) || (p2 != null);) {
            if (p1 != null) {
                p1 = p1.next;
            } else {
                headB = headB.next;
            }
            if (p2 != null) {
                p2 = p2.next;
            } else {
                headA = headA.next;
            }
        }
        for (; (headA != null) && (headB != null) && (headA != headB); headA = headA.next, headB = headB.next);
        return headA;
    }

    // Best Solution
    // Given LA = LA0 + LAB, LB = LB0 + LAB
    // This will always hold: LA0 + LAB + LB0 = LB0 + LAB + LA0
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        ListNode p1 = headA;
        ListNode p2 = headB;
        if (p1 == null || p2 == null) {
            return null;
        }
        for (;(p1 != p2);) {
            p1 = (p1 == null) ? headB : p1.next;
            p2 = (p2 == null) ? headA : p2.next;
        }
        return p1;
    }
}