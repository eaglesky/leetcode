#include <algorithm>
#include <deque>
#include <iostream>
#include <unordered_map>
#include <unordered_set>
#include <vector>
using namespace std;

void getPaths(unordered_map<string, pair<vector<string>, int> >& map,   
        string& tailStr, vector<string>& curPath, vector<vector<string> >& paths)
{

    curPath.push_back(tailStr);
    vector<string> parents = map[tailStr].first;
    if (parents.empty()) {
        paths.push_back(curPath);
        reverse(paths.back().begin(), paths.back().end());
    }

    for (int i = 0; i < parents.size(); ++i)
    {
        getPaths(map, parents[i], curPath, paths);
        
    }
    curPath.pop_back();
}

//Can be improved by replacing strings with their indices
vector<vector<string>> findLadders(string start, string end, unordered_set<string> &dict) {
    deque<string> strs;

    //map: string -> (indices of parents, level)
    unordered_map<string, pair<vector<string>, int> > map;
    strs.push_back(start);
    map[start] = make_pair(vector<string>(), 1);
    vector<vector<string> > result;
    int foundLevel = 0;

    while (!strs.empty()) {
        int sz = strs.size();
        string cur = strs.front();
        strs.pop_front();
        int curLevel = map[cur].second;
        if (curLevel == foundLevel)
            break;
        for (int j = 0; j < cur.size(); ++j)
                {
                    string temp = cur;
                    for (char c = 'a'; c <= 'z'; ++c)
                    {
                        if (cur[j] != c) {
                            temp[j] = c;
                            
                            if (temp == end) {
                                auto it = map.find(temp);
                                if (it == map.end()) {
                                    vector<string> tempParent{cur};
                                    map[temp] = make_pair(tempParent, curLevel+1);
                                    foundLevel = curLevel + 1;
                                }  
                                else if (it->second.second == curLevel + 1) 
                                    (it->second.first).push_back(cur);
                                                    
                            } else if (dict.find(temp) != dict.end()) {
                                auto it2 = map.find(temp);
                                if ((it2 != map.end()) && (it2->second.second == curLevel + 1)) {
                                    it2->second.first.push_back(cur); 
                                } else if (it2 == map.end()) {
                                    vector<string> tempParent{cur};
                                    map[temp] = make_pair(tempParent, curLevel + 1);
                                    strs.push_back(temp);
                                }

                            }
                        }
                    }
                }

           
    }
    vector<string> curPath;

    if (foundLevel) {
        
        getPaths(map, end, curPath, result);
    }
    return result;
}

int main(int argc, char** argv)
{
    unordered_set<string> dict = {"hot", "dot", "dog", "lot", "log"};
    string start = "hit";
    string end = "cog";
    vector<vector<string> > result = findLadders(start, end, dict);
    for (int i = 0; i < result.size(); ++i)
    {
        for (int j = 0; j < result[i].size(); ++j)
        {
            cout << result[i][j] << ", ";
        }
        cout << endl;
    }
    return 0;

}
