#include <iostream>
using namespace std;

struct ListNode {
    int val;
    ListNode* next;
    ListNode(int x):val(x), next(NULL) {}
};

ListNode *mergeTwoLists(ListNode *l1, ListNode *l2) {
    ListNode dummy(-1);
    ListNode* cur = &dummy;
    ListNode* p1 = l1;
    ListNode* p2 = l2;

    while (p1 && p2) {
        if (p1->val < p2->val) {
            cur->next = p1;
            p1 = p1->next;
        } else {
            cur->next = p2;
            p2 = p2->next;
        }
        cur = cur->next;
    }
    cur->next = p1 ? p1 : p2;
    return dummy.next;
}

int main(int argc, char** argv)
{
    return 0;
}
