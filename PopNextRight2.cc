#include <iostream>
#include <vector>
using namespace std;

struct TreeLinkNode {
    int val;
    TreeLinkNode* left;
    TreeLinkNode* right;
    TreeLinkNode* next;
    TreeLinkNode(int x): val(x), left(NULL), right(NULL), next(NULL){}
};

//The iterative on the book is more elegant!
void connect(TreeLinkNode *root) {
    TreeLinkNode* preLevel = root;
    while (preLevel) {
        TreeLinkNode* curLevelPre = NULL;
        TreeLinkNode* curLevelStart = NULL;
        while(preLevel) {
            if (preLevel->left || preLevel->right) {
                if (preLevel->right) {
                    if (preLevel->left) {
                        preLevel->left->next = preLevel->right;
                        if (curLevelPre)
                            curLevelPre->next = preLevel->left;
                    } else if (curLevelPre)
                        curLevelPre->next = preLevel->right;
                    curLevelPre = preLevel->right;
                } else {
                    if (curLevelPre)
                        curLevelPre->next = preLevel->left;
                    curLevelPre = preLevel->left;
                }
                if (!curLevelStart)
                    curLevelStart = preLevel->left ? preLevel->left : preLevel->right;
            }
            preLevel = preLevel->next;
        }
        preLevel = curLevelStart;
    }
}

int main(int argc, char** argv)
{
    TreeLinkNode* test = new TreeLinkNode(1);
    test->left = new TreeLinkNode(2);
    test->right = new TreeLinkNode(3);
    test->left->left = new TreeLinkNode(4);
    test->left->right = new TreeLinkNode(5);
    test->right->right = new TreeLinkNode(7);
    connect(test);
    return 0;
}

