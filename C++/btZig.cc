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

vector<vector<int> > zigzagLevelOrder(TreeNode *root) {
    vector<vector<int> > result;
    deque<TreeNode*> q;
    if (root) {
        bool isReverse = false;
        q.push_back(root);
        while (!q.empty()) {
            int sz = q.size();
            vector<int> level;
            for (int i = 0; i < sz; ++i)
            {
                TreeNode* cur = q.front();
                q.pop_front();
                level.push_back(cur->val);
                if (cur->left)
                    q.push_back(cur->left);
                if (cur->right)
                    q.push_back(cur->right);
            }
            if (isReverse)
                reverse(level.begin(), level.end());

            result.push_back(level);
            isReverse = !isReverse;
        }
    }
    return result;
}

int main(int argc, char** argv)
{
    TreeNode* test = new TreeNode(3);
    test->left = new TreeNode(9);
    test->right = new TreeNode(20);
    test->right->left = new TreeNode(15);
    test->right->right = new TreeNode(7);
    vector<vector<int> > result = zigzagLevelOrder(test);
    for (int i = 0; i < result.size(); ++i) {
        for (int j = 0; j < result[i].size(); ++j)
            cout << result[i][j] << ", ";
        cout << endl;
    }
    return 0;
}


