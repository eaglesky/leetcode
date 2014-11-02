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
    
    ListNode *deleteDuplicates1(ListNode *head) {
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

	ListNode *deleteDuplicates2(ListNode *head) {
        ListNode dummy(-1);
        dummy.next = head;
        ListNode* pre = &dummy;
        if (!pre->next)
            return head;
   
        while (pre->next) {
            ListNode* cur = pre->next;
            while ((cur->next) && (cur->next->val == cur->val)) {
                pre->next = NULL;
                ListNode* nextNode = cur->next;
                delete cur;
                cur = nextNode;
            }
            if (!pre->next) {
                pre->next = cur->next;
                delete cur;
            } else
                pre = pre->next;
        
        }
        
      
        return dummy.next;
    }

    //Another implementation of the above solution
    ListNode *deleteDuplicates(ListNode *head) {
        ListNode dummy(-1);
        dummy.next = head;
        ListNode* prev = &dummy;
        while (prev->next) {
            ListNode* cur = prev->next;
            bool deleted = false;
            for (;cur->next && (cur->val == cur->next->val); )
            {
                prev->next = cur->next;
                delete cur;
                cur = prev->next;
                deleted = true;
            }
            if (deleted) {
                prev->next = cur->next;
                delete cur;
            } else
                prev = prev->next;
        }
        
        return dummy.next;
    }

};
