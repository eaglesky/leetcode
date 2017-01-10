#include <iostream>
using namespace std;

struct ListNode {
      int val;
      ListNode *next;
      ListNode(int x) : val(x), next(NULL) {}
 }; 

void reorderList(ListNode *head) {
    ListNode* slow = head;
    ListNode* fast = head;
    if (!head)
        return;
    while (fast && fast->next) {
        fast = fast->next->next;
        slow = slow->next;
    }
    ListNode* head2 = slow->next;
    slow->next = NULL;

    ListNode dummy2(-1);
    dummy2.next = head2;
    head2 = &dummy2;
    for (ListNode* prev = head2->next; prev && prev->next;)
    {
        ListNode* cur = prev->next;
        prev->next = cur->next;
        cur->next = head2->next;
        head2->next = cur;
    }

    ListNode* head1 = head;
    head2 = head2->next;
    while (head2) {
        ListNode* next1 = head1->next;
        head1->next = head2;
        head2 = head2->next;
        head1->next->next = next1;
        head1 = next1;
    }

}

int main(int argc, char** argv)
{
    return 0;
}
