#include <iostream>
#include <vector>
using namespace std;

struct TreeNode {
    int val;
    TreeNode* left;
    TreeNode* right;
    TreeNode(int x): val(x), left(NULL), right(NULL){}
};

void inorderTraversalRec(TreeNode* root, vector<int>& result)
{
    if (root) {
        inorderTraversalRec(root->left, result);
        result.push_back(root->val);
        inorderTraversalRec(root->right, result);
    }
}

// Recursive solution, O(n) time and O(n) space
vector<int> inorderTraversal0(TreeNode *root) {
    vector<int> result;
    inorderTraversalRec(root, result);
    return result;
}

// Iterative solution using a stack, O(n) time and O(n) space 
vector<int> inorderTraversal1(TreeNode *root) {
    vector<int> result;
    vector<TreeNode*> stack;
    TreeNode* p = root;
    while(p || !stack.empty()) {
        if (p) {
            stack.push_back(p);
            p = p->left;
        } else {
            p = stack.back();
            stack.pop_back();
            result.push_back(p->val);
            p = p->right;
        }
    }

    return result;
}

// Morris inorder traversal, O(n) time and O(1) space
vector<int> inorderTraversal(TreeNode *root) {
    vector<int> result;
    TreeNode* cur = root;
    while(cur) {
        if (cur->left == NULL) {
            result.push_back(cur->val);
            cur = cur->right;
        } else {
            TreeNode* node = cur->left;
            while (node->right && (node->right != cur )) 
                node = node->right;
            if (node->right == NULL) {
                node->right = cur;
                cur = cur->left;
            } else {
                node->right = NULL;
                result.push_back(cur->val);
                cur = cur->right;
            }
        }
    }
    return result;
}

int main(int argc, char** argv)
{
    TreeNode* test = new TreeNode(1);
    test->right = new TreeNode(2);
    test->right->left = new TreeNode(3);
    vector<int> result = inorderTraversal(test);
    for (int i = 0; i < result.size(); ++i)
        cout << result[i] << endl;
    return 0;
}
