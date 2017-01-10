#include <iostream>
using namespace std;

struct ListNode {
      int val;
      ListNode *next;
      ListNode(int x) : val(x), next(NULL) {}
 }; 

ListNode *getIntersectionNode0(ListNode *headA, ListNode *headB) {
    ListNode* pa = headA;
    ListNode* pb = headB;
    if (!pa || !pb)
        return NULL;
    
    int lenA = 1;
    int lenB = 1;
    for (; pa->next; pa = pa->next, ++lenA);
    for (; pb->next; pb = pb->next, ++lenB);
    if (pa != pb)
        return NULL;
    
    pa = headA;
    pb = headB;
    if (lenA > lenB) {
        int diff = lenA - lenB;
        for (; diff > 0; --diff, pa = pa->next);
    } else {
        int diff = lenB - lenA;
        for (; diff > 0; --diff, pb = pb->next);
    }
    
    while (pa != pb) {
        pa = pa->next;
        pb = pb->next;
    }
    
    return pa;
    
}

//Improved solution with fewer variables
//Algorithm can be found in the solution of the problem on Leetcode
ListNode *getIntersectionNode(ListNode *headA, ListNode *headB) {
    ListNode* pa = headA;
    ListNode* pb = headB;
    
    if (!pa || !pb)
        return NULL;
        
    ListNode* aEnd = NULL;
    ListNode* bEnd = NULL;
    
    while (pa != pb) {
        if (!pa->next) {
            aEnd = pa;
            pa = headB;
        } else 
            pa = pa->next;
        
        if (!pb->next) {
            bEnd = pb;
            pb = headA;
        } else
            pb = pb->next;
        
        if (aEnd && bEnd && (aEnd != bEnd))
            return NULL;
    }
    return pa;
}

int main(int argc, char** argv)
{
    return 0;
}