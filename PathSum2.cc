#include <iostream>
#include <vector>
using namespace std;

struct TreeNode {
    int val;
    TreeNode* left;
    TreeNode* right;
    TreeNode(int x): val(x), left(NULL), right(NULL){}
};

void dfsPath(TreeNode* root, int sum, vector<int>& path, vector<vector<int> >& result)
{
    if (!root)
        return;
    if (!root->left && !root->right && (sum == root->val)) {
        path.push_back(root->val);
        result.push_back(path);
        path.pop_back();
        return;
    }

    path.push_back(root->val);
    sum -= root->val;
    if (root->left)
        dfsPath(root->left, sum, path, result);
    if (root->right)
        dfsPath(root->right, sum, path, result);
    path.pop_back();
}

vector<vector<int> > pathSum(TreeNode *root, int sum) {
    vector<vector<int> > result;
    vector<int> path;
    dfsPath(root, sum, path, result);
    return result;
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
    test->right->right->left = new TreeNode(5);
    test->right->right->right = new TreeNode(1);
    vector<vector<int> > result = pathSum(test, 22);
    for (int i = 0; i < result.size(); ++i)
    {
        for (int j = 0; j < result[i].size(); ++j)
            cout << result[i][j] << ", ";
        cout << endl;
    }
    return 0;
}

