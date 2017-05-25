/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
public class PalindromeLinkedList {
    
    private ListNode reverse(ListNode head) {
        ListNode newHead = null;
        for(ListNode cur = head; cur != null;) {
            ListNode post = cur.next;
            cur.next = newHead;
            newHead = cur;
            cur = post;
        }
        return newHead;
    }
    
    public boolean isPalindrome(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        if (slow == null) {
            return true;
        }
        for(; fast.next != null && fast.next.next != null; slow = slow.next, fast = fast.next.next);
        
        ListNode reversed = reverse(slow.next);
        for(slow = head, fast = reversed; fast != null; slow = slow.next, fast = fast.next) {
            if (slow.val != fast.val) {
                return false;
            }
        }
        return true;
    }
}