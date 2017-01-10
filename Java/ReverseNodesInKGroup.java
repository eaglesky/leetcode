/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
public class ReverseNodesInKGroup {
    public ListNode reverseKGroup0(ListNode head, int k) {
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

    // Another head insertion approach
    public ListNode reverseKGroup1(ListNode head, int k) {
        if (k <= 1) {
            return head;
        }
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode curHead = dummy;
        for (; ;) {
            ListNode runner = curHead;
            for (int i = 0; i < k && (runner != null); ++i, runner = runner.next);
            if (runner == null) {
                break;
            }
            ListNode newHead = null;
            runner = curHead.next;
            for (int i = 0; i < k; ++i) {
                ListNode next = runner.next;
                runner.next = newHead;
                newHead = runner;
                runner = next;
            }
            ListNode curTail = curHead.next;
            curHead.next.next = runner;
            curHead.next = newHead;
            curHead = curTail;
        }
        return dummy.next;
    }

    // Tail insertion
    public ListNode reverseKGroup(ListNode head, int k) {
        if (k <= 1) {
            return head;
        }
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode curHead = dummy;
        for (; ;) {
            ListNode runner = curHead;
            for (int i = 0; i < k && (runner != null); ++i, runner = runner.next);
            if (runner == null) {
                break;
            }
            ListNode curTail = runner;
            runner = curHead.next;
            ListNode nextHead = runner;
            while (runner != curTail) {
                curHead.next = runner.next;
                runner.next = curTail.next;
                curTail.next = runner;
                runner = curHead.next;
            }
            curHead = nextHead;
        }
        return dummy.next;
    }
}