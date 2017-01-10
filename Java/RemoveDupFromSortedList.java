/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
public class RemoveDupFromSortedList {
    public ListNode deleteDuplicates(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode pre = head;
        ListNode cur = head.next;
        for (; cur != null;) {
            if (cur.val == pre.val) {
                cur = cur.next;
                pre.next.next = null;
                pre.next = cur;
            } else {
                pre = pre.next;
                cur = cur.next;
            }
        }
        return head;
    }
}