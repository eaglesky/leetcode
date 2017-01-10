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

int sumNumbers0(TreeNode *root) {
    int sum = 0;
    dfs(root, 0, sum);
    return sum;
}

//Iterative solution
int sumNumbers(TreeNode *root) {
    int totalSum = 0;
    if (!root)
        return totalSum;
        
    vector<pair<TreeNode*, int> > stack{make_pair(root, root->val)};
    while (!stack.empty()) {
        pair<TreeNode*, int> curPair = stack.back();
        TreeNode* curNode = curPair.first;
        int curSum = curPair.second;
        stack.pop_back();
        
        if (!curNode->left && !curNode->right) {
            totalSum += curSum;
        }
        
        if (curNode->right) 
            stack.push_back(make_pair(curNode->right, 10*curSum + curNode->right->val));
        if (curNode->left)
            stack.push_back(make_pair(curNode->left, 10*curSum + curNode->left->val));
            
    }
    
    return totalSum;
}

int main(int argc, char** argv)
{
    TreeNode* test = new TreeNode(1);
    test->left = new TreeNode(2);
    test->right = new TreeNode(3);
    cout << sumNumbers(test) << endl;
    return 0;
}
