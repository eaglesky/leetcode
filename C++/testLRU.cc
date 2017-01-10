#include "LRUCache.h"
using namespace std;

int main(int argc, char** argv)
{
    LRUCache lru(2);
    lru.set(2, 1);
    lru.set(3, 2);
    lru.printContents();
    cout << "get(3) = " << lru.get(3) << endl;
    cout << "get(2) = " << lru.get(2) << endl;
    lru.set(4, 3);
    lru.printContents();
    cout << "get(2) = " << lru.get(2) << endl;
    cout << "get(3) = " << lru.get(3) << endl;
    cout << "get(4) = " << lru.get(4) << endl;
//    lru.printContents();
    return 0;
}
