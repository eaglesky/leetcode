#include <iostream>
using namespace std;

struct ListNode {
      int val;
      ListNode *next;
      ListNode(int x) : val(x), next(NULL) {}
 }; 

ListNode *rotateRight(ListNode *head, int k) {
    int len = 0;
    ListNode* tail = head;
    if (!head)
        return head;
    for (; tail->next; tail = tail->next, ++len );
    ++len;
    k = k % len;
    if (k == 0)
        return head;

    int count = len - k;
    ListNode* prev = head;
    for (int i = 0; i < count-1; ++i)
        prev = prev->next;
    tail->next = head;
    head = prev->next;
    prev->next = NULL;

    return head;
}

int main(int argc, char** argv)
{
    return 0;
}
