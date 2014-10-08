#include <deque>
#include <iostream>
#include <vector>
using namespace std;

struct ListNode {
    int val;
    ListNode* next;
    ListNode(int x): val(x), next(NULL){}
};

struct TreeNode {
    int val;
    TreeNode* left;
    TreeNode* right;
    TreeNode(int x): val(x), left(NULL), right(NULL){}
};

TreeNode* sortedList2BSTRec(ListNode* head, ListNode* tail) {

    if (!head || !tail || head == tail->next)
        return NULL;

    ListNode dummy(-1);
    dummy.next = head;
    ListNode* pSlowPre = &dummy;
    ListNode* pSlow = head;
    ListNode* pFast = head;
    while (pFast != tail && pFast->next != tail) {
        pFast = pFast->next->next;
        pSlow = pSlow->next;
        pSlowPre = pSlowPre->next;
    }
    TreeNode* root = new TreeNode(pSlow->val);

    if (pSlowPre != &dummy)
        root->left = sortedList2BSTRec(head, pSlowPre);

    root->right= sortedList2BSTRec(pSlow->next, tail);

    return root;
}

//Top-down recursive solution. O(nlogn) time and O(logn) space
TreeNode *sortedListToBST0(ListNode *head) {
    ListNode* p = head;
    if (!p)
        return NULL;
    while (p->next)
        p = p->next;
    return sortedList2BSTRec(head, p);
}

//Bottom-up recursive solution. O(n) time and O(logn) space
TreeNode* sortedList2BSTRec2(ListNode*& list, int start, int end)
{
    if (start > end)
        return NULL;
    int mid = start + (end - start) / 2;
    TreeNode* leftSub = sortedList2BSTRec2(list, start, mid-1);
    TreeNode* root = new TreeNode(list->val);
    root->left = leftSub;
    list = list->next;
    root->right  = sortedList2BSTRec2(list, mid+1, end);
    return root;

}

TreeNode *sortedListToBST1(ListNode *head) {
    ListNode* p = head;
    int i;
    for (i = 0; p ; ++i, p = p->next);
    return sortedList2BSTRec2(head, 0, i-1);
       
}

//Bottom-up solution improved
 TreeNode* sortedList2BST(ListNode*& head, int len)
    {
        if (len == 0)
            return NULL;
            
        int mid = len / 2;
        
        TreeNode* leftSub = sortedList2BST(head, mid);
        TreeNode* root = new TreeNode(head->val);
        head = head->next;
        TreeNode* rightSub = sortedList2BST(head, len - mid - 1);
        root->left = leftSub;
        root->right = rightSub;
        return root;
    }
    
    TreeNode *sortedListToBST(ListNode *head) {
        int len = 0;
        for (ListNode* p = head; p ; p = p->next, len++);
        return sortedList2BST(head, len);
    }


vector<vector<int> > levelOrder(TreeNode *root) {
    deque<TreeNode*> q;
    vector<vector<int> > result;

    if (root) {
        q.push_back(root);
        while (!q.empty()) {
            int sz = q.size();
            vector<int> curLevel;
            for (int i = 0; i < sz; ++i)
            {
                TreeNode* cur = q.front();
                q.pop_front();
                curLevel.push_back(cur->val);
                if (cur->left)
                    q.push_back(cur->left);
                if (cur->right)
                    q.push_back(cur->right);
            }
            result.push_back(curLevel);
        }
    }
    return result;
}

int main(int argc, char** argv)
{
    ListNode* test = new ListNode(1);
    ListNode* p = test;
    for (int i = 1; i < 8; ++i)
    {
        p->next = new ListNode(i+1);
        p = p->next;
    }

    TreeNode* testRoot = sortedListToBST(test);
    vector<vector<int> > result = levelOrder(testRoot);
    for (int i = 0; i < result.size(); ++i) {
        for (int j = 0; j < result[i].size(); ++j)
            cout << result[i][j] << ", ";
        cout << endl;
    }

    return 0;
}
