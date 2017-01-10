#include <iostream>
using namespace std;

struct ListNode {
      int val;
      ListNode *next;
      ListNode(int x) : val(x), next(NULL) {}
 }; 

bool hasCycle(ListNode *head) {
    ListNode* slow = head;
    if (!head)
        return false;
    ListNode* fast = head->next;
    while (fast && fast->next) {
        if (fast == slow)
            return true;
        fast = fast->next->next;
        slow = slow->next;
    }

    return false;
}

int main(int argc, char** argv)
{
    return 0;
}
