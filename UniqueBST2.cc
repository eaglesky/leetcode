#include <iostream>
#include <map>
#include <vector>
using namespace std;

struct TreeNode {
    int val;
    TreeNode* left;
    TreeNode* right;
    TreeNode(int x): val(x), left(NULL), right(NULL){}
};


//Use pointers and cache
vector<TreeNode* >* generateTreesRec0(int start, int end, 
        map<pair<int, int>, vector<TreeNode*>* >& cache) {
    vector<TreeNode* >* pResult = new vector<TreeNode*>;
    if (start > end) {
        pResult->push_back(NULL);
        return pResult;
    }

    auto it = cache.find(make_pair(start, end));
    if (it != cache.end())
        return it->second;

    for (int i = start; i <= end; ++i)
    {
       vector<TreeNode* >* pLeftNodes = generateTreesRec0(start, i-1, cache);
       vector<TreeNode* >* pRightNodes = generateTreesRec0(i+1, end, cache);

       for (auto l : *pLeftNodes)
       {
           for (auto r : *pRightNodes)
           {
                TreeNode* root = new TreeNode(i);
                root->left = l;
                root->right = r;
                pResult->push_back(root);
           }
       }
    }
    cache[make_pair(start, end)] = pResult;
    return pResult;
}

vector<TreeNode *> generateTrees0(int n) {
    map<pair<int, int>, vector<TreeNode*>* > cache;
    return *generateTreesRec0(1, n, cache);
}


//Use reference and without cache
void generateTreesRec(int start, int end, 
         vector<TreeNode*>& result) {
    if (start > end) {
        result.push_back(NULL);
        return ;
    }

    for (int i = start; i <= end; ++i)
    {
       vector<TreeNode*> leftNodes, rightNodes;
       generateTreesRec(start, i-1, leftNodes);
       generateTreesRec(i+1, end, rightNodes);

       for (auto l : leftNodes)
       {
           for (auto r : rightNodes)
           {
                TreeNode* root = new TreeNode(i);
                root->left = l;
                root->right = r;
                result.push_back(root);
           }
       }
    }
}

vector<TreeNode *> generateTrees(int n) {
    vector<TreeNode* > result;
    generateTreesRec(1, n, result);
    return result;
}


int main(int argc, char** argv)
{
    int n = atoi(argv[1]);
    vector<TreeNode *> result = generateTrees(n);
    return 0;
}
