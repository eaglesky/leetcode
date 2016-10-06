/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
public class ReverseNodesInKGroup {
    public ListNode reverseKGroup(ListNode head, int k) {
        if (k <= 1) {
            return head;
        }
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode p = dummy;
        for (; ;) {
            ListNode runner = p;
            for (int i = 0; i < k && (runner != null); ++i, runner = runner.next);
            if (runner == null) {
                break;
            }
            ListNode pTail = p.next;
            runner = pTail.next;
            for (int i = 0; i < k-1; ++i) {
                pTail.next = runner.next;
                runner.next = p.next;
                p.next = runner;
                runner = pTail.next;
            }
            p = pTail;
        }
        return dummy.next;
    }
}