#include <iostream>
using namespace std;

struct ListNode {
      int val;
      ListNode *next;
      ListNode(int x) : val(x), next(NULL) {}
 }; 

ListNode *swapPairs(ListNode *head) {
    ListNode dummy(-1);
    dummy.next = head;
    ListNode* prev = &dummy;

    while (prev->next && prev->next->next) {
        ListNode* post = prev->next->next;
        prev->next->next = post->next;
        post->next = prev->next;
        prev->next = post;
        prev = post->next;
    }

    return dummy.next;
}

int main(int argc, char** argv)
{
    return 0;
}
