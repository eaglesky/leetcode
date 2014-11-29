#include <iostream>
#include <vector>
#include <deque>
using namespace std;

struct TreeNode {
    int val;
    TreeNode* left;
    TreeNode* right;
    TreeNode(int x): val(x), left(NULL), right(NULL){}
};

TreeNode* arr2BT(vector<char>& vals)
{
    if (vals.empty())
        return NULL;
    TreeNode* root = new TreeNode(vals[0]-'0');
    deque<TreeNode*> q{root};
    TreeNode* cur = NULL;
    bool isLeft = true;
    for (int valId = 1; valId < vals.size(); ++valId, isLeft = !isLeft)
    {
        if (isLeft) {
            cur = q.front();
            q.pop_front();
            if (vals[valId] != '#') {
                cur->left = new TreeNode(vals[valId] - '0');
                q.push_back(cur->left);
            }
        } else {
            if (vals[valId] != '#') {
                cur->right = new TreeNode(vals[valId] - '0');
                q.push_back(cur->right);
            }
        }
    }

    return root;
}

vector<char> bt2Array(TreeNode* root)
{
    vector<char> result;
    deque<TreeNode*> q{root};
    if (!root)
        return result;
    
    while (!q.empty()) {
            TreeNode* cur = q.front();
            q.pop_front();
            if (!cur) {
                result.push_back('#');
            } else {
                result.push_back(cur->val + '0');    
                q.push_back(cur->left);
                q.push_back(cur->right);
            }

    }

    while (result.back() == '#') 
        result.pop_back();

    return result;
}

//Recursive solution
TreeNode* flip(TreeNode* root)
{
    if (!root)
        return NULL;

    TreeNode* newRoot = root->left ? flip(root->left) : root;
    if (root->left) {
        root->left->left = root->right;
        root->left->right = root;
        root->left = NULL;
        root->right = NULL;
    }
    
    return newRoot;
}

TreeNode* flipIter(TreeNode* root)
{
    TreeNode* paren = NULL;
    TreeNode* parenRight = NULL;
    if (!root)
        return NULL;

    while (root) {
        TreeNode* next = root->left;
        root->left = parenRight;
        parenRight = root->right;
        root->right = paren;
        paren = root;
        root = next;
         
    }

    return paren;
}

int main(int argc, char** argv)
{
    vector<char> test = {'1', '2', '3', '4', '5'};
    TreeNode* testTree = arr2BT(test);
    TreeNode* resultTree = flipIter(testTree);
    vector<char> result = bt2Array(resultTree);
    for (char c : result)
        cout << c << ", ";
    cout << endl;

    return 0;
}

