#include <iostream>
#include <vector>
using namespace std;

struct TreeNode {
    int val;
    TreeNode* left;
    TreeNode* right;
    TreeNode(int x): val(x), left(NULL), right(NULL){}
};

bool dfs(TreeNode* root, int sum, int curSum)
{
    if (!root->left && !root->right) {
        if (curSum + root->val == sum)
            return true;
        else
            return false;
    } 

    curSum += root->val;
    bool leftRes = false;
    bool rightRes = false;
    if (root->left)
        leftRes = dfs(root->left, sum, curSum);
    if (root->right)
        rightRes = dfs(root->right, sum, curSum);

    return leftRes || rightRes;
}

//The recursive solution on the book is better!
bool hasPathSum0(TreeNode *root, int sum) {
    if (!root)
        return false;

    return dfs(root, sum, 0);

}

//Iterative solution
bool hasPathSum(TreeNode *root, int sum) {
    vector<pair<TreeNode*, int> > stack;
    if (!root)
        return false;
    stack.push_back(make_pair(root, sum));
    while (!stack.empty()) {
        pair<TreeNode*, int> curPair = stack.back();
        TreeNode* p = curPair.first;
        int curSum = curPair.second;
        stack.pop_back();
        if (!p->left && !p->right && (curSum == p->val))
            return true;

        if (p->right)
            stack.push_back(make_pair(p->right, curSum - p->val));
        if (p->left)
            stack.push_back(make_pair(p->left, curSum - p->val));
    }
    return false;
}

int main(int argc, char** argv)
{
    TreeNode* test = new TreeNode(5);
    test->left = new TreeNode(4);
    test->right = new TreeNode(8);
    test->left->left = new TreeNode(11);
    test->right->left = new TreeNode(13);
    test->right->right = new TreeNode(4);
    test->left->left->left = new TreeNode(7);
    test->left->left->right = new TreeNode(2);
    test->right->right->right = new TreeNode(1);
    cout << hasPathSum(test, 22) << endl;
    return 0;
}


