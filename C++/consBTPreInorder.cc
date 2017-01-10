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
/*TreeNode* buildTreeRec(vector<int>& preorder, unordered_map<int, int>& inorderHash, int inStart, int inEnd, int& preId)
{
    TreeNode* bt = NULL;
    if ((preId >= preorder.size()) || (inStart > inEnd))
        return bt;

    auto it = inorderHash.find(preorder[preId]);
    int i;
    if (it == inorderHash.end())
        return bt;
    else {
        i = it->second;
        bt = new TreeNode(it->first);
    }

    if (inStart < i) {
        bt->left = buildTreeRec(preorder, inorderHash, inStart, i-1, ++preId);
    }

    if (i < inEnd)
        bt->right = buildTreeRec(preorder, inorderHash, i+1, inEnd, ++preId);

    return bt;
}

TreeNode *buildTree(vector<int> &preorder, vector<int> &inorder) {
    int preId = 0;
    unordered_map<int, int> inorderHash;
    for (int i = 0; i < inorder.size(); ++i)
    {
        inorderHash[inorder[i]] = i;
    }
    return buildTreeRec(preorder, inorderHash, 0, inorder.size()-1, preId);
}*/

//Another implementation of recursive solution
 TreeNode* buildTreeRec1(vector<int> &preorder,unordered_map<int, int>& inorderHash, int& preRoot, int inStart, int inEnd)
    {
        int n = preorder.size();
        
        if (preRoot >= n)
            return NULL;
            
        TreeNode* rootNode = new TreeNode(preorder[preRoot]);
        int inRoot = inorderHash[preorder[preRoot]];
        int inRootNext = inorderHash[preorder[preRoot+1]];
        
         if ((inRoot > inEnd) || (inRoot < inStart))
            return NULL;
            
      
        if ((inRootNext < inRoot) && (inRootNext >= inStart))
            rootNode->left = buildTreeRec1(preorder, inorderHash, ++preRoot, inStart, inRoot-1);
       
        inRootNext = inorderHash[preorder[preRoot+1]];
        if ((inRootNext > inRoot) && (inRootNext <= inEnd)) {
            rootNode->right = buildTreeRec1(preorder, inorderHash, ++preRoot, inRoot + 1, inEnd);
        } 
       
        
        return rootNode;
        
    }
    
    TreeNode *buildTree0(vector<int> &preorder, vector<int> &inorder) {
        unordered_map<int, int> map;
        int n = inorder.size();
        if (n == 0)
            return NULL;
            
        for (int i = 0; i < n; ++i)
            map[inorder[i]] = i;
        
        int preRoot = 0;
        return buildTreeRec(preorder, map, preRoot, 0, n-1);
        
        
    }

//More concise implementation
    TreeNode* buildTreeRec(vector<int>& preorder, int& preId, unordered_map<int, int>& inorderMap, int inStart, int inEnd)
    {
        TreeNode* root = new TreeNode(preorder[preId]);
        int inRootId = inorderMap[preorder[preId]];
        preId++;
        if (inRootId > inStart)
            root->left = buildTreeRec(preorder, preId, inorderMap, inStart, inRootId-1);
        
        if (inRootId < inEnd)
            root->right = buildTreeRec(preorder, preId, inorderMap, inRootId+1, inEnd);
            
        return root;
    }
    
    TreeNode *buildTree(vector<int> &preorder, vector<int> &inorder) {
        unordered_map<int, int> inorderMap;
        for (int i = 0; i < inorder.size(); ++i)
            inorderMap[inorder[i]] = i;
            
        int preId = 0;
        if (preorder.size() == 0)
            return NULL;
            
        return buildTreeRec(preorder, preId, inorderMap, 0, inorder.size()-1);
        
    }

int main(int argc, char** argv)
{
    vector<int> preTest{10, 8, 7, 9, 12, 11};
    vector<int> inTest{7, 8, 9, 10, 11, 12};
    TreeNode* bt = buildTree(preTest, inTest);
    vector<int> result = inorderTraversal(bt); 
    for (int i = 0; i < result.size(); ++i)
        cout << result[i] << endl;
    return 0;
}
