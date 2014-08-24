#include <iostream>
#include <vector>
using namespace std;

struct TreeNode {
    int val;
    TreeNode* left;
    TreeNode* right;
    TreeNode(int x): val(x), left(NULL), right(NULL){}
};

void dfs(TreeNode* root, int curNum, int& sum)
{
    if (!root)
        return;

    curNum = 10*curNum + root->val;
    if (!root->left && !root->right) {
        sum += curNum;
    }

    dfs(root->left, curNum, sum);
    dfs(root->right, curNum, sum);
}

int sumNumbers(TreeNode *root) {
    int sum = 0;
    dfs(root, 0, sum);
    return sum;
}

int main(int argc, char** argv)
{
    TreeNode* test = new TreeNode(1);
    test->left = new TreeNode(2);
    test->right = new TreeNode(3);
    cout << sumNumbers(test) << endl;
    return 0;
}
