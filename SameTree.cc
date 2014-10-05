#include <iostream>
#include <vector>
using namespace std;

struct TreeNode {
    int val;
    TreeNode* left;
    TreeNode* right;
    TreeNode(int x): val(x), left(NULL), right(NULL){}
};

//Recursive solution
bool isSameTree0(TreeNode *p, TreeNode *q) {
    if (!p && !q)
        return true;
    else if (p && q) {
        if ((p->val == q->val) && isSameTree(p->left, q->left) && isSameTree(p->right, q->right))
            return true;
    }

    return false;
}

// Iterative solution
bool isSameTree(TreeNode *p, TreeNode *q) {
    vector<TreeNode*> pq;
    vector<TreeNode*> qq;

    pq.push_back(p);
    qq.push_back(q);

    while (!pq.empty() && !qq.empty()) {
        TreeNode* curP = pq.back();
        TreeNode* curQ = qq.back();
        pq.pop_back();
        qq.pop_back();
        if (!curP && !curQ)
            continue;
        else if ((curP && curQ) && (curP->val == curQ->val)) {
            pq.push_back(curP->right);
            pq.push_back(curP->left);
            qq.push_back(curQ->right);
            qq.push_back(curQ->left);
        } else
            return false;
    }

    return true;
}

//Another iterative solution is using BFS.
int main(int argc, char** argv)
{
    return 0; 
}
