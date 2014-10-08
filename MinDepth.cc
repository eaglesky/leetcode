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

//Iterative solution. Recursive solution on the book is not as good as this
//one!
int minDepth0(TreeNode *root) {
    deque<TreeNode*> q;
    if (!root)
        return 0;
    q.push_back(root);
    int depth;
    for (depth = 1; !q.empty() ; ++depth)
    {
        int sz = q.size();
        for (int i = 0; i < sz; ++i)
        {
           TreeNode* cur = q.front();
           q.pop_front();
           if (!cur->left && !cur->right)
               return depth;
           if (cur->left) {
               q.push_back(cur->left);
           }
           if (cur->right)
               q.push_back(cur->right);
        }
    }
    return depth;
}

//My Recursive solution, O(n) time and O(n) space
int minDepth(TreeNode *root) {
        if (!root)
            return 0;
        
        int subDepth = 0;
        if (root->left && root->right)
            subDepth = min(minDepth(root->left), minDepth(root->right));
        else if (root->left)
            subDepth = minDepth(root->left);
        else if (root->right)
            subDepth = minDepth(root->right);
        else
            subDepth = 0;
            
        return subDepth+1;
    }

int main(int argc, char** argv)
{
    TreeNode* test = new TreeNode(1);
    test->left = new TreeNode(2);
    test->left->left = new TreeNode(4);
    test->right = new TreeNode(3);
    test->right->right = new TreeNode(5);
    cout << minDepth(test) << endl;
    return 0;
}
