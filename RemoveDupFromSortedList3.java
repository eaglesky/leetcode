public class RemoveDupFromSortedList3 {

    public static class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
    }

    public static ListNode deleteDuplicatesLeaveK(ListNode head, int k) {
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
            } else {
                pre.next = cur.next;
            }
            cur = cur.next;
        }
        return dummy.next;
    }

    private static void printLinkedList(ListNode head) {
        if (head == null) {
            return;
        }
        for (ListNode cur = head; cur != null; cur = cur.next) {
            System.out.print(cur.val + ", ");
        }
        System.out.println("");
    }

    public static void main(String[] args) {
        int k = 3;
        ListNode dummy = new ListNode(-1);
        ListNode tail = dummy;
        for (String arg: args) {
            ListNode curNode = new ListNode(Integer.parseInt(arg));
            tail.next = curNode;
            tail = curNode;
        }
        System.out.println("Input linked list: ");
        printLinkedList(dummy.next);

        System.out.println("After removing duplicates:");
        printLinkedList(deleteDuplicatesLeaveK(dummy.next, k));

    }
}