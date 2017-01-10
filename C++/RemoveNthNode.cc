#include <iostream>
using namespace std;

struct ListNode {
      int val;
      ListNode *next;
      ListNode(int x) : val(x), next(NULL) {}
 }; 

ListNode *removeNthFromEnd(ListNode *head, int n) {
    ListNode dummy(-1);
    dummy.next = head;
    ListNode* last = &dummy;
    for (int i = 0; (last) && (i <= n); ++i)
        last = last->next;
    ListNode* prev = &dummy;
    for (; last ; prev = prev->next, last = last->next);
    ListNode* deleted = prev->next;
    prev->next = deleted->next;
    delete deleted;

    return dummy.next;
}

int main(int argc, char** argv)
{
    return 0;
}
