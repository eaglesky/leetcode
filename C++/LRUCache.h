#include <iostream>
#include <unordered_map>
using namespace std;

class LRUCache{
    
private:

struct ListNode {
    int key;
    int val;
    ListNode* prev;
    ListNode* next;
    ListNode(int k, int v) : key(k), val(v), prev(NULL), next(NULL){};
};

    unordered_map<int, ListNode*> map;
    ListNode* dummyHead;
    ListNode* dummyTail;
    int maxSize;
  
    
public:
    LRUCache(int capacity) {
        maxSize = (capacity >= 0) ? capacity : 0;
        
        dummyHead = new ListNode(-1, -1);
        dummyHead->next = new ListNode(-1, -1);
        dummyHead->next->prev = dummyHead;
        dummyTail = dummyHead->next;
    }
   
    ~LRUCache() {
        for (ListNode* p = dummyHead; p; )
        {
            ListNode* pnext = p->next;
            delete p;
            p = pnext;
        }
    }

    void printContents() {
        for (ListNode* p = dummyHead->next; p != dummyTail; p = p->next)
        {
            cout <<  p->key << ", " << p->val << endl; 
        }
    }

    int get(int key) {
        
        if (map.find(key) == map.end())
            return -1;
            
        ListNode* cur = map[key];
        cur->prev->next = cur->next;
        cur->next->prev = cur->prev;
        cur->next = dummyHead->next;
        dummyHead->next->prev = cur;
        dummyHead->next = cur;
        cur->prev = dummyHead;

        return cur->val;
    }
    
    void set(int key, int value) {
        if (map.find(key) != map.end()) {
            get(key);
            map[key]->val = value;
        }
        else {
            ListNode* newNode = new ListNode(key, value);
            map[key] = newNode;
            newNode->next = dummyHead->next;
            dummyHead->next->prev = newNode;
            dummyHead->next = newNode;
            newNode->prev = dummyHead;
            
            if (map.size() > maxSize) {
                ListNode* deleted = dummyTail->prev;
                int deletedKey = deleted->key;
                deleted->prev->next = deleted->next;
                deleted->next->prev = deleted->prev;
                delete deleted;
                map.erase(deletedKey);
            }
            
        }
    }
};
