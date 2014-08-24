#include <iostream>
#include <vector>
#include <queue>
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

//Naive solution. O(nk) time and O(1) space
ListNode *mergeKLists0(vector<ListNode *> &lists) {
    int k = lists.size();
    if (k == 0)
        return NULL;
    ListNode* pre = lists[0];
    for (int i = 1; i < k; ++i)
    {
        pre = mergeTwoLists(pre, lists[i]); 
    }
    return pre;
}

//Priority Queue solution, O(nlogk) time and O(k) space
struct CompareListNodes {
    bool operator()(const ListNode* l1, const ListNode* l2)
    {
        return l1->val > l2->val;
    }
};

ListNode *mergeKLists1(vector<ListNode *> &lists) {
    priority_queue<ListNode*, vector<ListNode*>, CompareListNodes> pq;
    ListNode dummy(-1);
    ListNode* p = &dummy;
    for (int i = 0; i < lists.size(); ++i)
    {
        if (lists[i])
            pq.push(lists[i]);
    }


    while (!pq.empty()) {
        ListNode* cur = pq.top();
        pq.pop();
        p->next = cur;
        cur = cur->next;
        if (cur)
            pq.push(cur);
        p = p->next;
    }

    return dummy.next;
}

// Divide and Conquer. O(nlogk) time and O(logk) space 
ListNode* merge(vector<ListNode*>& lists, int start, int end)
{
    if (start == end)
        return lists[start];

    int mid = start + (end - start) / 2;
    ListNode* l1 = merge(lists, start, mid);
    ListNode* l2 = merge(lists, mid+1, end);

    return mergeTwoLists(l1, l2);
}

ListNode *mergeKLists(vector<ListNode *> &lists) {
    if (lists.size() == 0)
        return NULL;
    return merge(lists, 0, lists.size()-1);
}

int main(int argc, char** argv)
{
    ListNode* l1 = new ListNode(1);
    ListNode* l2 = new ListNode(0);
    vector<ListNode*> lists;
    lists.push_back(l1);
    lists.push_back(l2);

    ListNode* res = mergeKLists(lists);
    while (res) {
        cout << res->val << ", ";
        res = res->next;
    }
    cout << endl;
    return 0;
}
