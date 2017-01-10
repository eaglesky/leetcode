/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
public class PartitionList {
    public ListNode partition(ListNode head, int x) {
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode pre = dummy;
        ListNode newTail = dummy;
        for(; pre.next != null;) {
            if (pre.next.val < x) {
                if (pre == newTail) {
                    pre = pre.next;
                    newTail = newTail.next;
                } else {
                    ListNode cur = pre.next;
                    pre.next = pre.next.next;
                    cur.next = newTail.next;
                    newTail.next = cur;
                    newTail = cur;
                }
            } else {
                pre = pre.next;
            }
        }
        return dummy.next;
    }
}