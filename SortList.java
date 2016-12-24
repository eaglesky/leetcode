

 
public class SortList {
	 public static class ListNode {
	     int val;
	     ListNode next;
	     ListNode(int x) { val = x; }
	 }    
    private ListNode[] mergeTwoLists(ListNode head1, ListNode head2) {
        ListNode dummy = new ListNode(-1);
        ListNode newTail = dummy;
        while (head1 != null && head2 != null) {
            if (head1.val <= head2.val) {
                newTail.next = head1;
                head1 = head1.next;
                newTail = newTail.next;
            } else {
                newTail.next = head2;
                head2 = head2.next;
                newTail = newTail.next;
            }
        }
        newTail.next = (head1 == null) ? head2 : head1;
        for (; newTail.next != null; newTail = newTail.next);
        return new ListNode[]{dummy.next, newTail};
    }
    
    //Bottom-up merge sort. O(nlogn) time and O(1) space
    public ListNode sortList(ListNode head) {
        int k = 1;
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        while (true) {
            ListNode preTail = dummy;
            ListNode head1 = preTail.next;
            for (; ;) {
                ListNode tail1 = head1;
                for (int count = 0; count < k - 1 && tail1 != null; tail1 = tail1.next, ++count);
                if (tail1 == null) {
                    break;
                }
                ListNode head2 = tail1.next, tail2 = head2;
                tail1.next = null;
                for (int count = 0; count < k - 1 && tail2 != null; tail2 = tail2.next, ++count);
                ListNode nextHead1 = null;
                if (tail2 != null) {
                    nextHead1 = tail2.next;
                    tail2.next = null;
                }
                ListNode[] mergedNodes = mergeTwoLists(head1, head2);
                preTail.next = mergedNodes[0];
                preTail = mergedNodes[1];
                head1 = nextHead1;
                preTail.next = head1;
            }
            if (head1 == dummy.next) {
                break;
            }
            k = k << 1;
        }
        return dummy.next;
    }

    private static ListNode createLinkedListFromArray(int[] nums) {
    	ListNode dummy = new ListNode(-1);
    	ListNode newTail = dummy;
    	for (int num : nums) {
    		newTail.next = new ListNode(num);
    		newTail = newTail.next;
    	}
    	return dummy.next;
    }

    private static void printListNodes(ListNode node) {
    	for (ListNode curNode = node; curNode != null; curNode = curNode.next) {
    		System.out.print(curNode.val + ", ");
    	}
    	System.out.println("");
    }

    public static void main(String[] args) {
    	int[] test = {4, 19, 14, 5, -3, 1, 8, 5, 11, 15};
    	ListNode head = createLinkedListFromArray(test);
    	System.out.println("Input: ");
    	printListNodes(head);
    	SortList sl = new SortList();
    	ListNode newHead = sl.sortList(head);
    	printListNodes(newHead);
    }
}