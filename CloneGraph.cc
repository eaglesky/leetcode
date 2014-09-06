#include <deque>
#include <iostream>
#include <unordered_map>
#include <unordered_set>
#include <vector>
using namespace std;

struct UndirectedGraphNode {
    int label;
    vector<UndirectedGraphNode*> neighbors;
    UndirectedGraphNode(int x):label(x) {}
};

//BFS
//O(n) time and O(n) space
UndirectedGraphNode *cloneGraph(UndirectedGraphNode *node) {
    if (!node)
        return NULL;
    deque<UndirectedGraphNode* > q;
    unordered_map<UndirectedGraphNode*, UndirectedGraphNode*> map;
    q.push_back(node);
    UndirectedGraphNode* ret = new UndirectedGraphNode(node->label);
    map[node] = ret;

    while (!q.empty()) {
        UndirectedGraphNode* cur = q.front();
        q.pop_front();
        for (int i = 0; i < (cur->neighbors).size(); ++i)
        {
            UndirectedGraphNode* curAdj = (cur->neighbors)[i];
            auto it = map.find(curAdj);
            if (it == map.end()) {
                q.push_back(curAdj);
                UndirectedGraphNode* newNode = new UndirectedGraphNode(curAdj->label);
                map[curAdj] = newNode;
                map[cur]->neighbors.push_back(newNode);
            } else {
                map[cur]->neighbors.push_back(it->second);
            }
        }
    }

    return ret;
}

int main(int argc, char** argv)
{
    return 0;
}
