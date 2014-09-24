#include <iostream>
using namespace std;

struct RandomListNode {
    int label;
    RandomListNode *next, *random;
    RandomListNode(int x) : label(x), next(NULL), random(NULL) {}
};

//O(n) time and O(1) space
    RandomListNode *copyRandomList(RandomListNode *head) {

        RandomListNode* p = head;
        if (!head)
            return NULL;
        
        while (p) {
            RandomListNode* pnext = p->next;
            p->next = new RandomListNode(p->label);
            p->next->next = pnext;
            p = pnext;
        }
    
        RandomListNode* newHead = head->next;
        for (p = head; p ; )
        {
           p->next->random = p->random ? p->random->next : NULL;
           p = p->next->next;
        }
    
        for (p = head; p; )
        {
            RandomListNode* pnext = p->next->next;
            p->next->next = pnext ? pnext->next : NULL;
            p->next = pnext;
            p = pnext;
        }
        
        return newHead;
    
    }

//Another solution is using a hashmap to store the mapping from original nodes
//to their copies. It takes two passes and O(n) extra space 


int main(int argc, char** argv)
{
    RandomListNode head(-1);
    RandomListNode* newHead = copyRandomList(&head);
    RandomListNode* p = newHead;
    while(p) {
        string randomLabel = p->random ? to_string(p->random->label) : "NULL";
        cout << p->label << ", " << randomLabel << endl;
        p = p->next;
    }
    return 0;
}
