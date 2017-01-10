#include <algorithm>
#include <deque>
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
int maxDepth(TreeNode* root) {
   
    if (!root)
        return 0;

    return 1 + max(maxDepth(root->left), maxDepth(root->right));
}

//Iterative solution using level order traversal.
int maxDepth0(TreeNode *root) {
    deque<TreeNode*> q;
    if (!root)
        return 0;
    q.push_back(root);
    int depth;
    for (depth = 0; !q.empty() ; ++depth)
    {
        int sz = q.size();
        for (int i = 0; i < sz; ++i)
        {
           TreeNode* cur = q.front();
           q.pop_front();
           if (cur->left) 
               q.push_back(cur->left);
           if (cur->right)
               q.push_back(cur->right);
        }
    }
    return depth;
}

int main(int argc, char** argv)
{
    TreeNode* test = new TreeNode(1);
    test->left = new TreeNode(2);
    test->left->left = new TreeNode(4);
    test->right = new TreeNode(3);
    test->right->right = new TreeNode(5);
    test->right->left = new TreeNode(6);
    test->right->left->left = new TreeNode(7);
    cout << maxDepth(test) << endl;
    return 0;
}
