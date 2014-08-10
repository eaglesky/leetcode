/**
 * Definition for singly-linked list.
 * struct ListNode {
 *     int val;
 *     ListNode *next;
 *     ListNode(int x) : val(x), next(NULL) {}
 * };
 */
class Solution {
public:
    ListNode *deleteDuplicates0(ListNode *head) {
        if (!head)
            return head;
            
        ListNode dummy(-1);
        dummy.next = head;
        head = &dummy;
    
        ListNode* p = head->next->next;
        
        while (p || head->next->next) {
            if (p && (p->val == head->next->val)) {
            
                p = p->next;
             
            } else if (head->next->next == p) {
                head = head->next;
                p = p->next;
            } else {
                while (head->next != p) {
                    ListNode* temp = head->next->next;
                    delete head->next;
                    head->next = temp;
                }
                p = p ? p->next : NULL;
            }
            if (!head->next)
                break;
        }
        
        return dummy.next;
    }
    
    ListNode *deleteDuplicates(ListNode *head) {
        if (!head)
            return head;
            
        ListNode dummy(-1);
        dummy.next = head;
        head = &dummy;
        
        ListNode* cur = head->next;
        while (cur) {
            
            if ((cur->next) && (cur->next->val == cur->val)) {
                int curVal = cur->val;
                while (cur && (cur->val == curVal )) {
                    cur = cur->next;
                    delete head->next;
                    head->next = cur;
                }
            } else {
                cur = cur->next;
                head = head->next;
            }
        }
        
        return dummy.next;
    }
};