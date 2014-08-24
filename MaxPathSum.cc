#include <climits>
#include <iostream>
#include <vector>
using namespace std;

struct TreeNode {
    int val;
    TreeNode* left;
    TreeNode* right;
    TreeNode(int x): val(x), left(NULL), right(NULL){}
};

int dfs(TreeNode* root, int& maxSum)
{
   if (!root)
       return 0;

   int leftSum = dfs(root->left, maxSum);
   int rightSum = dfs(root->right, maxSum);
   int maxOneSum = max(leftSum, rightSum);
   int retSum = (maxOneSum > 0) ? (root->val + maxOneSum) : (root->val);
   int curMaxSum = root->val;
   if (leftSum > 0)
       curMaxSum += leftSum;
   if (rightSum > 0)
       curMaxSum += rightSum;
   if (curMaxSum > maxSum)
       maxSum = curMaxSum;

   return retSum;
}

int maxPathSum(TreeNode *root) {
    int result = INT_MIN;
    if (!root)
        return 0;
    dfs(root, result);
    return result;
}

int main(int argc, char** argv)
{
    TreeNode* test = new TreeNode(1);
    test->left = new TreeNode(2);
    test->right = new TreeNode(3);
    cout << maxPathSum(test) << endl;
    return 0;
}
