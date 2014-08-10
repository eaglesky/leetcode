#include <iostream>
using namespace std;

struct ListNode {
      int val;
      ListNode *next;
      ListNode(int x) : val(x), next(NULL) {}
 }; 

ListNode *partition(ListNode *head, int x) {
        ListNode* dummy = new ListNode(-1);
        dummy->next = head;
        
        ListNode* pLess = dummy;
        ListNode* prev = dummy;
        ListNode* cur = head;
        
        while (cur) {
            if (cur->val < x) {
                if (pLess == prev) {
                    pLess = pLess->next;
                    prev = prev->next;
                    cur = cur->next;
                    continue;
                }
                prev->next = cur->next;
                cur->next = pLess->next;
                pLess->next = cur;
                pLess = cur;
                cur = prev->next;
            } else {
                prev = cur;
                cur = cur->next;
            }
        }
        
        return dummy->next;
    }

int main(int argc, char** argv)
{
    ListNode* test = new ListNode(1);
    ListNode* result= partition(test, 2);

    ListNode* p = result;
    while (p) {
        cout << p->val << endl;
        p = p->next;
    }
    return 0;
}
