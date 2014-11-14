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
vector<vector<string>> findLadders0(string start, string end, unordered_set<string> &dict) {
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

//Another similar solution using indices instead of strings
    void dfsPath0(string& curStr, unordered_map<string, pair<vector<int>, int> >& used, vector<string>& q, vector<string>& path, vector<vector<string> >& result)
    {
        int n = used[curStr].first.size();
        path.push_back(curStr);
        if (n == 0) {
            result.push_back(path);
            reverse(result.back().begin(), result.back().end());
        
        }
        
     
        for (int i = 0; i < n; ++i)
        {
            string curParen = q[used[curStr].first[i]];
            dfsPath0(curParen, used, q, path, result);
        }
        
        path.pop_back();
    }
    
    vector<vector<string>> findLadders1(string start, string end, unordered_set<string> &dict) {
        unordered_map<string, pair<vector<int>, int> > used;
        vector<int> v;
        used[start] = make_pair(v, 1);
        vector<string> q{start};
        
        int front = 0;
      
        while (front < q.size()) {
           
                
                string cur = q[front];
                if (cur == end)
                    break;
                int curLev = used[cur].second;
                
                for (int j = 0; j < cur.size(); ++j)
                {
                    string cur2 = cur;
                    for (char c = 'a'; c <= 'z'; ++c)
                    {
                        
                        cur2[j] = c;
                        
                        if ((used.find(cur2) != used.end()) && (used[cur2].second == (curLev + 1))) {
                            used[cur2].first.push_back(front);
                        }
                        
                        if (((cur2 == end) || (dict.find(cur2) != dict.end())) && (used.find(cur2) == used.end())) {
                            q.push_back(cur2);
                            vector<int> vcur2{front};
                            used[cur2] = make_pair(vcur2, curLev+1);
                        }
                    }
                }
                
                front++;
            
        }
        
        vector<string> path;
        vector<vector<string> > result;
        
        if (used.find(end) != used.end())
            dfsPath0(end, used, q, path, result );
            
        return result;
    }

//Simple implementation using map of strings
void dfsPaths(unordered_map<string, vector<string> >& parents, string& cur, vector<string>& curPath, vector<vector<string> >& result)
{
    vector<string> parentStrs = parents[cur];
    curPath.push_back(cur);
    
    if (parentStrs.empty()) {
        result.push_back(curPath);
        reverse(result.back().begin(), result.back().end());
        return;
    }
    
    for (int i = 0; i < parentStrs.size(); ++i)
    {
        dfsPaths(parents, parentStrs[i], curPath, result);
        curPath.pop_back();
    }
}

vector<vector<string>> findLadders(string start, string end, unordered_set<string> &dict) {
    vector<vector<string> > result;
    unordered_map<string, int> strLevs{{start, 0}};
    deque<string> q{start};
    unordered_map<string, vector<string> > parents;
    vector<string> init;
    parents[start] = init;
    
    int level = 0;
    for (; !q.empty() && (q.front() != end);)
    {
        int curSz = q.size();
        level++;
        for (int i = 0; i < curSz; ++i)
        {
            string cur = q.front();
            if (cur == end)
                break;
                
            q.pop_front();
            for (int j = 0; j < cur.size(); ++j)
            {
                string temp = cur;
                for (char c = 'a'; c <= 'z'; ++c)
                {
                    if (cur[j] == c)
                        continue;
                        
                    temp[j] = c;
                    if ((dict.find(temp) != dict.end()) || (temp == end)) {
                        if (strLevs.find(temp) == strLevs.end()) {
                            q.push_back(temp);
                            strLevs[temp] = level;
                            parents[temp].push_back(cur);
                        } else {
                            int lastLev = strLevs[temp];
                            if (lastLev == level) {
                                parents[temp].push_back(cur);
                            }
                        }
                    }
                  
                }
            }
        }
        
       
    }
    
    if (q.empty())
        return result;
    
    vector<string> curPath;
    dfsPaths(parents, end, curPath, result);
    return result;
    
}

int main(int argc, char** argv)
{
    unordered_set<string> dict = {"hot","cog","dog","tot","hog","hop","pot","dot"};
    string start = "hot";
    string end = "dog";
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
