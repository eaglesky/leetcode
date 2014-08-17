#include <iostream>
#include <vector>
using namespace std;

struct TreeNode {
    int val;
    TreeNode* left;
    TreeNode* right;
    TreeNode(int x): val(x), left(NULL), right(NULL){}
};

TreeNode* flattenRec(TreeNode* root)
{
    if (!root)
        return NULL;

    TreeNode* leftEnd = flattenRec(root->left);
    TreeNode* rightEnd = flattenRec(root->right);
    

    if (leftEnd) {
        leftEnd->right = root->right;
        root->right = root->left;
    } else
        leftEnd = root;

    root->left = NULL;
    return rightEnd ? rightEnd: leftEnd;
}

//Recursive solution, Nine Chapter has another shorter solution
void flatten0(TreeNode *root) {
    flattenRec(root);
}

//Iterative solution
void flatten(TreeNode* root) {
    vector<TreeNode*> s;
    if(!root)
        return;
    s.push_back(root);
    while(!s.empty()) {
       TreeNode* cur = s.back();
       s.pop_back();

       if (cur->right)
            s.push_back(cur->right);
       if (cur->left)
           s.push_back(cur->left);
       
       cur->left = NULL;
       if (!s.empty())
           cur->right = s.back();
    }
}

int main(int argc, char** argv)
{
   TreeNode* test = new TreeNode(1);
/*   test->left = new TreeNode(2);
   test->left->left = new TreeNode(3);
   test->left->right = new TreeNode(4);
   test->right = new TreeNode(5);
   test->right->right = new TreeNode(6);
  */
   test->left = new TreeNode(2);
   flatten(test);
   TreeNode* p = test;
   while(p) {
       cout << p->val << endl;
       p = p->right;
   }
   return 0;
}
