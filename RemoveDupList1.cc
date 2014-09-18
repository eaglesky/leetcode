#include <iostream>
using namespace std;

  struct ListNode {
      int val;
      ListNode* next;
      ListNode(int x) : val(x), next(NULL) {}
  };    

ListNode *deleteDuplicates(ListNode *head) {
        ListNode* pre = head;
        if (!pre)
            return head;
        ListNode* cur = pre->next;
        while (cur) {
            if (pre->val == cur->val) {
                pre->next = cur->next;
                ListNode* node = cur;
                cur = cur->next;
                delete node;
            } else {
                pre = cur;
                cur = cur->next;
            }
        }
        
        return head;
    }

int main(int argc, char** argv)
{
	return 0;
}
