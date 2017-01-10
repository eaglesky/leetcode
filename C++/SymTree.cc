#include <iostream>
#include <deque>
using namespace std;

struct TreeNode {
    int val;
    TreeNode* left;
    TreeNode* right;
    TreeNode(int x): val(x), left(NULL), right(NULL){}
};

//Recursive solution
bool isSymmetricRec(TreeNode* left, TreeNode* right)
{
    if (!left && !right)
        return true;
    else if (left && right && (left->val == right->val) 
            && isSymmetricRec(left->left, right->right)
            && isSymmetricRec(left->right, right->left))
        return true;
    else
        return false;
}

bool isSymmetric0(TreeNode *root) {
    return !root || isSymmetricRec(root->left, root->right);    
}

//Iterative solution
bool isSymmetric(TreeNode *root) {
    deque<TreeNode*> q;
    q.push_back(root);
    while (!q.empty()) {
        int sz = q.size();
        int i = 0;
        int j = sz-1;
        for (; i < j; i++, j--) {
            if (q[i] && q[j] && (q[i]->val == q[j]->val))
                continue;
            else if (!q[i] && !q[j])
                continue;
            else 
                return false;
        }
        for (i = 0; i < sz; ++i)
        {
            TreeNode* cur = q.front();
            q.pop_front();

            if (cur) {
                
                q.push_back(cur->left);
                q.push_back(cur->right);
            }
        }
    }

    return true;
}

// Improved iterative solution is in the C++ Leetcode Solutions book
bool isSymmetric(TreeNode *root) {
    if (!root)
        return true;
    vector<TreeNode*> stack;
    stack.push_back(root->right);
    stack.push_back(root->left);
    while (!stack.empty()) {
        TreeNode* l = stack.back();
        stack.pop_back();
        TreeNode* r = stack.back();
        stack.pop_back();
        
        if (!l && !r)
            continue;
        else if (l && r && (l->val == r->val)) {
            stack.push_back(r->right);
            stack.push_back(l->left);
            stack.push_back(r->left);
            stack.push_back(l->right);
        } else
            return false;
    }
    
    return true;
}

int main(int argc, char** argv)
{
    TreeNode* test = new TreeNode(1);
   
    cout << isSymmetric(test) << endl;
    return 0;
}
