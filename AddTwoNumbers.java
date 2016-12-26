/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
public class AddTwoNumbers {
    
    //O(n) time and O(1) space
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(-1);
        ListNode newTail = dummy;
        int carry = 0;
        for (ListNode p1 = l1, p2 = l2; p1 != null || p2 != null;) {
            int value1 = (p1 == null) ? 0 : p1.val;
            int value2 = (p2 == null) ? 0 : p2.val;
            int value = value1 + value2 + carry;
            carry = value / 10;
            newTail.next = new ListNode(value % 10);
            newTail = newTail.next;
            if (p1 != null) {
                p1 = p1.next;
            }
            if (p2 != null) {
                p2 = p2.next;
            }
        }
        if (carry > 0) {
            newTail.next = new ListNode(carry);
        }
        return dummy.next;
    }
}