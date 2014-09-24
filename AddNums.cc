#include <iostream>
using namespace std;

struct ListNode {
    int val;
    ListNode* next;
    ListNode(int x) : val(x), next(NULL) {}
};

ListNode *addTwoNumbers(ListNode *l1, ListNode *l2) {
    int carry = 0;
    ListNode dummy(-1);
    ListNode* prev = &dummy;
    while (l1 || l2) {
        int val1 = l1 ? l1->val : 0;
        int val2 = l2 ? l2->val : 0;
        int val = val1 + val2 + carry;
        carry = val / 10;
        val %= 10;
        prev->next = new ListNode(val);
        prev = prev->next;
        l1 = l1 ? l1->next : NULL;
        l2 = l2 ? l2->next : NULL;
    }
    if (carry > 0)
        prev->next = new ListNode(carry);

    return dummy.next;
}

int main(int argc, char** argv)
{

    return 0;
}
