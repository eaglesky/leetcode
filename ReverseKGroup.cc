#include <iostream>
using namespace std;

struct ListNode {
      int val;
      ListNode *next;
      ListNode(int x) : val(x), next(NULL) {}
 }; 

ListNode *reverseKGroup(ListNode *head, int k) {
    ListNode dummy(-1);
    dummy.next = head;
    head = &dummy;
    while (1) {
        ListNode* tail = head->next;
        for (int i = 0; i < k; ++i)
        {
            if (!tail)
                return dummy.next;
            tail = tail->next;
        }

        ListNode* prev = head->next;
        while (prev->next != tail)
        {
            ListNode* cur = prev->next;
            prev->next = cur->next;
            cur->next = head->next;
            head->next = cur;
        }
        head = prev;
    }

    return dummy.next;
}

int main(int argc, char** argv)
{
    return 0;
}
