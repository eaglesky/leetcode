#include <iostream>
using namespace std;

struct ListNode {
    int val;
    ListNode* next;
    ListNode(int x):val(x), next(NULL) {}
};

void merge(ListNode* l1,  ListNode* l2, ListNode*& head, ListNode*& tail)
{
    ListNode dummy(-1);
    dummy.next = head;
    head = &dummy;
    while (l1 && l2) {
        if (l1->val <= l2->val) {
            head->next = l1;
            l1 = l1->next;
        } else {
            head->next = l2;
            l2 = l2->next;
        }
        head = head->next;
    }
    tail = l1 ? l1 : l2;
    head->next = tail;
    while (tail->next)
        tail = tail->next;
    head = dummy.next;
    tail->next = NULL;
}

ListNode *sortList(ListNode *head) {
    int k = 1;
    ListNode dummy(-1);
    dummy.next = head;

    while (1) {
        ListNode* prev = &dummy;
        ListNode* l1 = dummy.next;
        ListNode* l2 = dummy.next;
        while (1) {
            int i = 0;
            for (l2 = l1; (i < k) && l2; ++i ) 
            {
                if (i == k-1) {
                    ListNode* tmp = l2;
                    l2 = l2->next;
                    tmp->next = NULL;
                } else
                    l2 = l2->next;
            }   

            if (l2) {
                ListNode* mergedHead = NULL;
                ListNode* mergedTail = NULL;
                ListNode* post = l2;
                int j = 0;
                for (; (j < k) && post; ++j)
                {
                    if (j == k-1) {
                        ListNode* tmp = post;
                        post = post->next;
                        tmp->next = NULL;
                    } else 
                         post = post->next;
                }

                merge(l1, l2, mergedHead, mergedTail);
                prev->next = mergedHead;
                mergedTail->next = post;

                l1 = post;
                prev = mergedTail;
            } else
                break;
        }
        k *= 2;
        if (l1 == dummy.next)
            break;
    }
    return dummy.next;
}

int main(int argc, char** argv)
{
    ListNode* test = new ListNode(5);
    test->next = new ListNode(4);
    test->next->next = new ListNode(3);
    test->next->next->next = new ListNode(8);
    ListNode* res = sortList(test);
    while (res) {
        cout << res->val << ", ";
        res = res->next;
    }
    cout << endl;
    return 0;

}
