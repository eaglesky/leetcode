#include <iostream>
#include <vector>
using namespace std;

struct TreeNode {
    int val;
    TreeNode* left;
    TreeNode* right;
    TreeNode(int x): val(x), left(NULL), right(NULL){}
};

void preOrderTraversalRec(TreeNode* root, vector<int>& result)
{
    if (!root)
        return;

    result.push_back(root->val);
    preOrderTraversalRec(root->left, result);
    preOrderTraversalRec(root->right, result);
}

//Recursive solution, O(n) time and O(n) space
vector<int> preorderTraversal0(TreeNode* root) {
    vector<int> result;
    preOrderTraversalRec(root, result);
    return result;
}

//Iterative solution, O(n) time and O(n) space
vector<int> preorderTraversal1(TreeNode* root) {
    vector<TreeNode*> stack{root};
    vector<int> result;

    while (!stack.empty()) {
        TreeNode* curNode = stack.back();
        stack.pop_back();
        if (curNode) {
            result.push_back(curNode->val);
            stack.push_back(curNode->right);
            stack.push_back(curNode->left);
        }
    }
    return result;
}

//Morris Traversal
   vector<int> preorderTraversal(TreeNode *root) {
        vector<int> result;
        TreeNode* cur = root;
        while (cur) {
            
            if (cur->left) {
                TreeNode* post = cur->left;
                while ((post->right) && (post->right != cur))
                    post = post->right;
                if (post->right == NULL) {
                    post->right = cur;
                    result.push_back(cur->val);
                    cur = cur->left;
                } else {
                    post->right = NULL;
                    cur = cur->right;
                }
            } else {
                result.push_back(cur->val);
                cur = cur->right;
            }
        }
        
        return result;
    }

int main(int argc, char** argv)
{
    TreeNode* test = new TreeNode(1);
    test->right = new TreeNode(2);
    test->right->left = new TreeNode(3);
    test->left = new TreeNode(4);
    vector<int> result = preorderTraversal(test);
    for (int i = 0; i < result.size(); ++i)
        cout << result[i] << endl;
    return 0;
}
