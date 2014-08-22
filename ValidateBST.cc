#include <iostream>
#include <vector>
using namespace std;

struct TreeNode {
    int val;
    TreeNode* left;
    TreeNode* right;
    TreeNode(int x): val(x), left(NULL), right(NULL){}
};

//Recursive solution: Root must not be NULL!(Solution on the book is better!)
bool isValidBSTRec(TreeNode* root, int& max, int& min)
{
    bool res = true;
    int maxLeft, minLeft, maxRight, minRight;
    maxLeft = minLeft = maxRight = minRight = root->val;
    if (root->left) {
        res = isValidBSTRec(root->left, maxLeft, minLeft) 
            && (maxLeft < root->val);
        if (!res)
            return res;
    }

    if (root->right) {
        res = res && isValidBSTRec(root->right, maxRight, minRight)
            && (minRight > root->val);
        if (!res)
            return res;
    }
    min = minLeft;
    max = maxRight;
    return res;
}

bool isValidBST0(TreeNode *root) {
    if (!root)
        return true;

    int max, min;
    return isValidBSTRec(root, max, min);
}

// Iterative solution using Morris inorder traversal(O(n) time but slow,
// O(1)space)
bool isValidBST(TreeNode *root) {
    TreeNode* cur = root;
    TreeNode* pre = NULL;
    bool result = true;
    while(cur) {
        if (!cur->left) {
            if (pre && (pre->val >= cur->val))
                result = false;

            pre = cur;
            cur = cur->right;
        } else {
            TreeNode* node = cur->left;
            while (node->right && (node->right != cur)) {
                node = node->right;
            }
            if (!node->right) {
                node->right = cur;
                cur = cur->left;
            } else {
                
                if (pre && (pre->val >= cur->val))
                    result =  false;
                node->right = NULL;
                pre = cur;
                cur = cur->right;
            }
        }
    }

    return result;
}

int main(int argc, char** argv)
{
   /* TreeNode* test = new TreeNode(10);
    test->left = new TreeNode(8);
    test->left->right = new TreeNode(20);
    test->right = new TreeNode(11);*/

/*    TreeNode* test = new TreeNode(1);
    test->left = new TreeNode(2);*/

    TreeNode* test = new TreeNode(10);
    test->left = new TreeNode(5);
    test->right = new TreeNode(15);
    test->right->left = new TreeNode(6);
    test->right->right = new TreeNode(20);

    cout << isValidBST(test) << endl;
    return 0;
}
