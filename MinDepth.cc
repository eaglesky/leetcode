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
int minDepth(TreeNode *root) {
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
