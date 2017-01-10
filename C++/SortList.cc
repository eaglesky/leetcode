#include <iostream>
using namespace std;

struct ListNode {
    int val;
    ListNode* next;
    ListNode(int x):val(x), next(NULL) {}
};

    ListNode* merge2Lists(ListNode* p1, ListNode* p2)
{
    ListNode dummy(-1);
    ListNode* prev = &dummy;
    while (p1 && p2) {
        if (p1->val < p2->val) {
            prev->next = p1;
            p1 = p1->next;
        } else {
            prev->next = p2;
            p2 = p2->next;
        }
        prev = prev->next;
    }
    prev->next = p1 ? p1 : p2;
  
    return dummy.next;
}

ListNode* sortListRec(ListNode* head)
{
    ListNode* slow = head;
    ListNode* fast = head;
    for (; fast && fast->next && fast->next->next ; fast = fast->next->next, slow = slow->next);
    if (!slow || !slow->next) {
        return head;
    }
    ListNode* p2 = slow->next;
    slow->next = NULL;
    ListNode* p1 = head;
    ListNode* head1 = sortListRec(p1);
    ListNode* head2 = sortListRec(p2);
  
    return merge2Lists(head1, head2);
}

//Recursive solution
ListNode* sortList(ListNode* head) {
    
        
    ListNode* tail = NULL;
    return sortListRec(head);
}

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

ListNode *sortList0(ListNode *head) {
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
    ListNode* test = new ListNode(2);
    test->next = new ListNode(1);
   
    ListNode* res = sortList(test);
    while (res) {
        cout << res->val << ", ";
        res = res->next;
    }
    cout << endl;
    return 0;

}
