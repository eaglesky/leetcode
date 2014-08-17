#include <iostream>
#include <vector>
using namespace std;

struct TreeNode {
    int val;
    TreeNode* left;
    TreeNode* right;
    TreeNode(int x): val(x), left(NULL), right(NULL){}
};

//Iterative solution, O(n) time and O(n) space
vector<int> postorderTraversal(TreeNode *root) {
    vector<int> result;
    vector<TreeNode*> stack;
    TreeNode* cur = root;
    do {
        while (cur) {
            stack.push_back(cur);
            cur = cur->left;
        }
        TreeNode* pre = NULL;
        while(!stack.empty()) {
            cur = stack.back();
            if (cur->right == pre) {
                result.push_back(cur->val);
                pre = cur;
                stack.pop_back();
            } else {
                cur = cur->right;
                break;
            }
        }

    } while (!stack.empty());
    
    return result;
}

int main(int argc, char** argv)
{
    TreeNode* test = new TreeNode(1);
    test->right = new TreeNode(2);
    test->right->left = new TreeNode(3);
    vector<int> result = postorderTraversal(test);
    for (int i = 0; i < result.size(); ++i)
        cout << result[i] << endl;
 
    return 0;
}
