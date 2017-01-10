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

TreeNode* sortedArray2BSTRec(vector<int>& num, int start, int end)
{
    if (start > end)
        return NULL;

    int mid = (start + end) / 2;
    TreeNode* root = new TreeNode(num[mid]);
    root->left = sortedArray2BSTRec(num, start, mid-1);
    root->right = sortedArray2BSTRec(num, mid+1, end);
    return root;
}

TreeNode *sortedArrayToBST(vector<int> &num) {
    return sortedArray2BSTRec(num, 0, num.size()-1);
}

vector<vector<int> > levelOrder(TreeNode *root) {
    deque<TreeNode*> q;
    vector<vector<int> > result;

    if (root) {
        q.push_back(root);
        while (!q.empty()) {
            int sz = q.size();
            vector<int> curLevel;
            for (int i = 0; i < sz; ++i)
            {
                TreeNode* cur = q.front();
                q.pop_front();
                curLevel.push_back(cur->val);
                if (cur->left)
                    q.push_back(cur->left);
                if (cur->right)
                    q.push_back(cur->right);
            }
            result.push_back(curLevel);
        }
    }
    return result;
}

int main(int argc, char** argv)
{
    vector<int> test{1, 2, 3, 4, 5, 6, 7};
    TreeNode* testRoot = sortedArrayToBST(test);
    vector<vector<int> > result = levelOrder(testRoot);
    for (int i = 0; i < result.size(); ++i) {
        for (int j = 0; j < result[i].size(); ++j)
            cout << result[i][j] << ", ";
        cout << endl;
    }

    return 0;
}
