public class RemoveDupFromSortedList3 {

    public class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
    }

    public ListNode deleteDuplicatesLeaveK(ListNode head, int k) {
        if (head == null || head.next == null) {
            return head;
        }
        if (k < 0) {
            return head;
        }
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode pre = dummy;
        ListNode cur = pre.next;

        for (; cur != null; ) {
            for(; (cur.next != null) && (cur.val == cur.next.val); cur = cur.next);
            int i = 0;
            for (; i < k && (pre.next != cur); pre = pre.next, ++i);
            if (i < k || ((k == 0) && (pre.next == cur))) {
                pre = pre.next;
                cur = cur.next;
            } else {
                pre.next = cur.next;
                cur = cur.next;
            }
        }
        return dummy.next;
    }

    public static void main(String[] args) {
        int k = 2;
        
    }
}