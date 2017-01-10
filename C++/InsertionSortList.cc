#include <iostream>
using namespace std;

struct ListNode {
    int val;
    ListNode* next;
    ListNode(int x):val(x), next(NULL) {}
};

ListNode *insertionSortList(ListNode *head) {
    if (!head || !head->next)
        return head;
    ListNode dummy(-1);
    dummy.next = head;
    ListNode* pre = head;
    ListNode* cur = head->next;
    while (cur) {
        ListNode* p = &dummy;
        while ((p->next->val <= cur->val) && (p != pre) )
            p = p->next;
        if (p != pre) {
            pre->next = cur->next;
            cur->next = p->next;
            p->next = cur;
            cur = pre->next;
        } else {
            pre = cur;
            cur = cur->next;
        }
            
    }
    return dummy.next;
}

int main(int argc, char** argv)
{
    ListNode* test = new ListNode(5);
    test->next = new ListNode(4);
    test->next->next = new ListNode(3);
    test->next->next->next = new ListNode(8);
    ListNode* res = insertionSortList(test);
    while (res) {
        cout << res->val << ", ";
        res = res->next;
    }
    cout << endl;
    return 0;
}
