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
}