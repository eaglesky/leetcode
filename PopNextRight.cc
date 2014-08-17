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

// Iterative solution.  Recursive solution (not desirable) is on the solution
// book
void connect(TreeLinkNode *root) {
    if (!root)
        return;
    TreeLinkNode* cur = root;
    cur->next = NULL;
    while (cur->left) {
        TreeLinkNode* p = cur;
        while(p) {
            p->left->next = p->right;
            if (p->next)
                p->right->next = p->next->left;
            else
                p->right->next = NULL;
            p = p->next;
            
        }
        cur = cur->left;
    }
}

int main(int argc, char** argv)
{
    TreeLinkNode* test = new TreeLinkNode(1);
    test->left = new TreeLinkNode(2);
    test->right = new TreeLinkNode(3);
    test->left->left = new TreeLinkNode(4);
    test->left->right = new TreeLinkNode(5);
    test->right->left = new TreeLinkNode(6);
    test->right->right = new TreeLinkNode(7);
    connect(test);
    return 0;
}

