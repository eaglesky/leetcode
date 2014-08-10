#include "LRUCache.h"
using namespace std;

int main(int argc, char** argv)
{
    LRUCache lru(2);
    lru.set(2, 1);
    lru.set(2, 2);
    cout << lru.get(2) << endl;
    lru.set(1, 1);
    lru.set(4, 1);
    cout << lru.get(2) << endl;
//    lru.printContents();
    return 0;
}
