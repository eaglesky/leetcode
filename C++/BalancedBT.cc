#include <cstdlib>
#include <iostream>
#include <vector>
using namespace std;

struct TreeNode {
    int val;
    TreeNode* left;
    TreeNode* right;
    TreeNode(int x): val(x), left(NULL), right(NULL){}
};

bool checkBalanced(TreeNode* node, int& depth)
{
    int depthLeft, depthRight;
    if (!node) {
        depth = 0;
        return true;
    }
    bool checkLeft = checkBalanced(node->left, depthLeft) ;
    bool checkRight = checkBalanced(node->right, depthRight);
    depth = (depthLeft > depthRight) ? (depthLeft+1) : (depthRight+1);
    if (checkLeft && checkRight && (abs(depthLeft - depthRight) <= 1)) {
        return true;
    } else
        return false;
}

bool isBalanced(TreeNode *root) {
    int depth = 0;
    return checkBalanced(root, depth); 
}

int main(int argc, char** argv)
{
    TreeNode* test = new TreeNode(1);
//    test->right = new TreeNode(2);
//    test->right->left = new TreeNode(3);
    cout<< isBalanced(test) << endl; 
    return 0;
}
