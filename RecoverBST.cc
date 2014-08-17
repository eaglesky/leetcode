#include <algorithm>
#include <iostream>
#include <vector>
using namespace std;

struct TreeNode {
    int val;
    TreeNode* left;
    TreeNode* right;
    TreeNode(int x): val(x), left(NULL), right(NULL){}
};

void detect(pair<TreeNode*, TreeNode*>& swapped, TreeNode* pre, TreeNode* cur)
{
    if (pre && pre->val > cur->val) {
        if (!swapped.first) {
            swapped.first = pre;
        }
        swapped.second = cur;
        
    }

}
void recoverTree(TreeNode *root) {
    
    TreeNode* cur = root;
    TreeNode* pre = NULL;
    pair<TreeNode*, TreeNode*> swapped(NULL, NULL);
    while(cur) {
        if (!cur->left) {
            detect(swapped, pre, cur);
            pre = cur;
            cur = cur->right;
        } else {
            TreeNode* node  = cur->left;
            while (node->right && (node->right != cur)) 
                node = node->right;

            if (!node->right) {
                node->right = cur;
                cur = cur->left;
            } else {
                detect(swapped, pre, cur);
                pre = cur;
                cur = cur->right;
                node->right = NULL;
            }

        }
    }
   
    if (swapped.first && swapped.second)
        swap(swapped.first->val, swapped.second->val);
}

int main(int argc, char** argv)
{
   /* TreeNode* test = new TreeNode(10);
    test->left = new TreeNode(11);
    test->left->left = new TreeNode(7);
    test->left->right = new TreeNode(9);
    test->right = new TreeNode(12);
    test->right->left = new TreeNode(8);*/

    TreeNode* test = new TreeNode(0);
    test->left = new TreeNode(1);
    recoverTree(test);
    return 0;
}
