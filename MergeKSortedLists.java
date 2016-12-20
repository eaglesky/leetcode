/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
public class MergeKSortedLists {
    
    //Heap solution. O(nlogk) time and O(k) space. n is the number of all the nodes.
    public ListNode mergeKLists0(ListNode[] lists) {
        if (lists.length == 0) {
            return null;
        }
        PriorityQueue<ListNode> pq = new PriorityQueue<>(lists.length, new Comparator<ListNode>() {
            public int compare(ListNode node1, ListNode node2) {
                if (node1.val > node2.val) {
                    return 1;
                } else if (node1.val < node2.val) {
                    return -1;
                } else {
                    return 0;
                }
            }
        });
        for (ListNode node : lists) {
            if (node != null) {
                pq.offer(node);
            }
        }
        ListNode dummyHead = new ListNode(-1);
        ListNode tail = dummyHead;
        while (pq.size() > 1) {
            ListNode minNode = pq.poll();
            if (minNode.next != null) {
                pq.offer(minNode.next);
            }
            tail.next = minNode;
            tail = tail.next;
        }
        tail.next = pq.poll();
        return dummyHead.next;
    }
    
    //Divide and conquer solution. O(nlogk) time and O(logk) space.
    private ListNode mergeTwoLists(ListNode[] lists, int low, int high) {
        if (low == high) {
            return lists[low];
        }
        int mid = low + ((high - low) >> 1);
        ListNode mergedList1 = mergeTwoLists(lists, low, mid);
        ListNode mergedList2 = mergeTwoLists(lists, mid+1, high);
        ListNode dummy = new ListNode(0);
        ListNode tail = dummy;
        ListNode p1 = mergedList1;
        ListNode p2 = mergedList2;
        while (p1 != null && p2 != null) {
            if (p1.val < p2.val) {
                tail.next = p1;
                p1 = p1.next;
            } else {
                tail.next = p2;
                p2 = p2.next;
            }
            tail = tail.next;
        }
        tail.next = (p1 == null) ? p2 : p1;
        return dummy.next;
    }
    
    public ListNode mergeKLists(ListNode[] lists) {
        if (lists.length == 0) {
            return null;
        }
        return mergeTwoLists(lists, 0, lists.length - 1);
    }
}