import java.util.*;

public class RemoveDupFromSortedList3 {

    public static class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
    }

    public static ListNode deleteDuplicatesLeaveK0(ListNode head, int k) {
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

    //Better implementation than above
    public static ListNode deleteDuplicatesLeaveK1(ListNode head, int k) {
        if (k < 0) {
            return head;
        } else if (k == 0) {
            return null;
        }
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        int count = 0;
        ListNode prev = dummy;
        for(ListNode cur = prev.next; cur != null;) {
            if (prev == dummy || cur.val != prev.val) {
                count = 1;
            } else {
                count++;
            }
            if (count == k) {
                ListNode post = cur;
                for(; post.next != null && post.next.val == post.val; post = post.next);
                if (post != cur) {
                    cur.next = post.next;
                }
            }
            prev = cur;
            cur = cur.next;      
        }
        return dummy.next;
    }

    //Best implementation
    public static ListNode deleteDuplicatesLeaveK2(ListNode head, int k) {
        if (k < 0) {
            return head;
        } else if (k == 0) {
            return null;
        }
        ListNode p = head;
        int counter = 0;
        for(; p != null; p = p.next) {
            if (counter == k - 1) {
                ListNode cur = p.next;
                for(; cur != null && cur.val == p.val; cur = cur.next);
                p.next = cur;
            }
            if (p.next != null && p.next.val == p.val) {
                counter++;
            } else {
                counter = 0;
            }
        }
        return head;
    }

    //Another good implementation
    public static ListNode deleteDuplicatesLeaveK(ListNode head, int k) {
        if (k < 0) {
            return head;
        } else if (k == 0) {
            return null;
        }
        ListNode p = head;
        int counter = 0;
        for(; p != null;) {
            if (p.next != null && p.next.val == p.val) {
                if (counter == k - 1) {
                    p.next = p.next.next;
                } else {
                    counter++;
                    p = p.next;
                }
            } else {
                counter = 0;
                p = p.next;
            }
        }
        return head;
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

    private static ListNode createNodeFromArray(int[] myArgs) {
        ListNode dummy = new ListNode(-1);
        ListNode tail = dummy;
        for (int arg: myArgs) {
            ListNode curNode = new ListNode(arg);
            tail.next = curNode;
            tail = curNode;
        }
        return dummy.next;
    }

    public static void main(String[] args) {
        int[][] tests = new int[][]{
            {1, 1, 1, 1, 3, 4, 4, 4, 4, 5, 5, 5, 6},
            {1, 2, 3, 3, 3, 3, 4, 4, 4},
            {2},
            {}
        };
        for (int[] test : tests) {
            System.out.println("Input linked list: " + Arrays.toString(test));
            for (int k = 0; k <= 4; ++k) {
                ListNode head = createNodeFromArray(test);
                System.out.println("k = " + k);
                printLinkedList(deleteDuplicatesLeaveK(head, k));                
            }
            System.out.println("");
        }
    }
}