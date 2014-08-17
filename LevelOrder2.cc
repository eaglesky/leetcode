#include <algorithm>
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

vector<vector<int> > levelOrderBottom(TreeNode *root) {
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
    reverse(result.begin(), result.end());
    return result;
}

int main(int argc, char** argv)
{
    TreeNode* test = new TreeNode(3);
    test->left = new TreeNode(9);
    test->right = new TreeNode(20);
    test->right->left = new TreeNode(15);
    test->right->right = new TreeNode(7);
    vector<vector<int> > result = levelOrderBottom(test);
    for (int i = 0; i < result.size(); ++i) {
        for (int j = 0; j < result[i].size(); ++j)
            cout << result[i][j] << ", ";
        cout << endl;
    }
    return 0;
}
