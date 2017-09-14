/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
public class InsertionSortList {
    public ListNode insertionSortList(ListNode head) {
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        
        for (ListNode preNode = dummy; preNode.next != null;) {
            ListNode curNode = preNode.next;
            //Don't move the node if the value of the previous node is smaller than the current one
            if (preNode.val <= curNode.val) {
                preNode = preNode.next;
                continue;
            }
            ListNode runner = dummy;
            for (; runner != preNode && runner.next.val <= curNode.val; runner = runner.next);
            if (runner != preNode) {
                preNode.next = curNode.next;
                curNode.next = runner.next;
                runner.next = curNode;
            } else {
                preNode = preNode.next;
            }
        }
        return dummy.next;
    }

    //Second try
    //The outer loop is the same as typical insertion sort,
    //while the inner loop iterates from the beginning to the current node,
    //following the direction of the pointers.
    public ListNode insertionSortList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        for (ListNode pre = head; pre.next != null;) {
            ListNode preRunner = dummy;
            for (; preRunner != pre && preRunner.next.val <= pre.next.val; preRunner = preRunner.next);
            if (preRunner != pre) {
                ListNode cur = pre.next;
                pre.next = cur.next;
                cur.next = preRunner.next;
                preRunner.next = cur;
            } else {
                pre = pre.next;
            }
        }
        return dummy.next;
    }
}