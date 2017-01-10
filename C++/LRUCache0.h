#include <iostream>
#include <unordered_map>

class LRUCache{
private:
    struct CacheNode {
        int key;
        int val;
        CacheNode* prev;
        CacheNode* next;
        CacheNode(int k, int v): key(k), val(v), prev(NULL), next(NULL) {}
    };
    
    CacheNode* dummyHead;
    CacheNode* dummyTail;
    int cacheSize;
    int itemNum;
    std::unordered_map<int, CacheNode*> cacheMap;
    
public:

    LRUCache(int capacity) {
        dummyHead = new CacheNode(-1, -1);
        dummyTail = new CacheNode(-1, -1);
        dummyHead->next = dummyTail;
        dummyTail->prev = dummyHead;
        cacheSize = capacity;
        itemNum = 0;
        cacheMap.reserve(capacity);
    }
    
    ~LRUCache() {
        CacheNode* p = dummyHead;
        while(p) {
            CacheNode* temp = p;
            p = p->next;
            delete temp;
        }
    }
   
    void moveToFront(CacheNode* p)
    {
        p->prev->next = p->next;
        p->next->prev = p->prev;
        p->next = dummyHead->next;
        dummyHead->next->prev = p;
        dummyHead->next = p;
        p->prev = dummyHead;
    }

    int get(int key) {
        if (cacheMap.find(key) == cacheMap.end())
            return -1;
            
        CacheNode* cur = cacheMap[key];
        moveToFront(cur);

        return dummyHead->next->val;
        
    }
    
    void set(int key, int value) {
        if (get(key) == -1) {
            CacheNode* newNode = new CacheNode(key, value);
            cacheMap[key] = newNode;
            newNode->next = dummyHead->next;
            dummyHead->next->prev = newNode;
            dummyHead->next = newNode;
            newNode->prev = dummyHead;
            if (itemNum < cacheSize) {
                itemNum++;
            } else {
                CacheNode* tail = dummyTail->prev;
                tail->prev->next = dummyTail;
                dummyTail->prev = tail->prev;

                int deletedKey = tail->key;
                delete tail;
                cacheMap.erase(deletedKey);
            }
        } else {
            CacheNode* p = cacheMap[key];
            p->val = value;
            moveToFront(p);

        } 
    }

    void printContents() {
        CacheNode* p = dummyHead->next;
        while (p != dummyTail) {
            std::cout << "(" << p->key << ", " << p->val << ")" << std::endl;
            p = p->next;
        }
        std::cout << std::endl;
    }
};
