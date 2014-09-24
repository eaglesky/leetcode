#include <iostream>
using namespace std;

struct ListNode {
      int val;
      ListNode *next;
      ListNode(int x) : val(x), next(NULL) {}
 }; 

ListNode *detectCycle(ListNode *head) {

    ListNode* slow = head;
    ListNode* fast = head;
    
    while (fast && fast->next) {
        fast = fast->next->next;
        slow = slow->next;

        if (fast == slow)
            break;
    } 

    if ((!fast) || (!fast->next))
        return NULL;

    for (ListNode* slow2 = head; slow2 != slow; slow2 = slow2->next,  slow = slow->next;);
    return slow;
}

int main(int argc, char** argv)
{
    return 0;
}
