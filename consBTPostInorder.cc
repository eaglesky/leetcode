#include <iostream>
#include <unordered_map>
#include <vector>
using namespace std;

struct TreeNode {
    int val;
    TreeNode* left;
    TreeNode* right;
    TreeNode(int x): val(x), left(NULL), right(NULL){}
};

// Iterative solution using a stack, O(n) time and O(n) space 
vector<int> inorderTraversal(TreeNode *root) {
    vector<int> result;
    vector<TreeNode*> stack;
    TreeNode* p = root;
    while(p || !stack.empty()) {
        if (p) {
            stack.push_back(p);
            p = p->left;
        } else {
            p = stack.back();
            stack.pop_back();
            result.push_back(p->val);
            p = p->right;
        }
    }

    return result;
}


//Recursive solution. O(n) time and O(n) space
TreeNode* buildTreeRec(vector<int>& postorder, unordered_map<int, int>& inorderHash, int inStart, int inEnd, int& postId)
{
    TreeNode* bt = NULL;
    if ((postId < 0) || (inStart > inEnd))
        return bt;

    auto it = inorderHash.find(postorder[postId]);
    int i;
    if (it == inorderHash.end())
        return bt;
    else {
        i = it->second;
        bt = new TreeNode(it->first);
    }

    if (i < inEnd)
        bt->right = buildTreeRec(postorder, inorderHash, i+1, inEnd, --postId);

    if (inStart < i) {
        bt->left = buildTreeRec(postorder, inorderHash, inStart, i-1, --postId);
    }


    return bt;
}

TreeNode *buildTree(vector<int> &postorder, vector<int> &inorder) {
    int postId = postorder.size()-1;
    unordered_map<int, int> inorderHash;
    for (int i = 0; i < inorder.size(); ++i)
    {
        inorderHash[inorder[i]] = i;
    }
    return buildTreeRec(postorder, inorderHash, 0, inorder.size()-1, postId);
}

int main(int argc, char** argv)
{
   // vector<int> postTest{7, 9, 8, 11, 12, 10};
   // vector<int> inTest{7, 8, 9, 10, 11, 12};
//    vector<int> postTest{2, 1};
//    vector<int> inTest{1, 2};
    vector<int> inTest{10, 30, 40, 50,60, 70, 90};
    vector<int> postTest{10, 40, 30, 60, 90, 70, 50};

    TreeNode* bt = buildTree(postTest, inTest);
    vector<int> result = inorderTraversal(bt); 
    for (int i = 0; i < result.size(); ++i)
        cout << result[i] << endl;
    return 0;
}
