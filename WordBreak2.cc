#include <iostream>
#include <unordered_set>
#include <vector>
using namespace std;

//DP + DFS
void dfsPrevs(vector<vector<int> >& prevs, int id, string& s, string& curString, vector<string>& result)
{
    if (id == -1) {
        result.push_back(curString);
        return;
    }

    for (int i = 0; i < prevs[id].size(); ++i)
    {
        int strStart = prevs[id][i]+1;
        string temp = s.substr(strStart, id-strStart+1);
        if (curString != "")
            temp += " ";
        temp += curString;
        dfsPrevs(prevs, prevs[id][i], s, temp, result);
    }
}

vector<string> wordBreak0(string s, unordered_set<string> &dict) {
    int l = s.size();
    vector<string> result;
    if (l == 0) {
        if (dict.find(s) != dict.end()) {
            result.push_back(s);
        }
        return result;
    }

    vector<bool> f(l, false);
    vector<vector<int> > prevs(l, vector<int>());

    for (int i = 0; i < l; ++i)
    {
        for (int j = i; j >= 0; --j)
        {
           if ((dict.find(s.substr(j, i-j+1)) != dict.end()) && ((j < 1) || f[j-1])) {
                f[i] = true;
                prevs[i].push_back(j-1);
            }
        }
    }

    string curString = "";
    dfsPrevs(prevs, l-1, s, curString, result);
    return result;

}

//Memoization
bool dfsWordBreak(string& s, unordered_set<string>& dict, int start, vector<bool>& cache, string& curStrings, vector<string>& result)
{
    if (start == s.size()) {
        result.push_back(curStrings);
        return true;
    }

    cache[start] = false;
    for (int i = start; i < s.size(); ++i)
    {
        string curString = s.substr(start, i-start+1);
        string temp = curString;
        if (curStrings != "")
            temp = " " + temp;
        temp = curStrings + temp;
        if ((dict.find(curString) != dict.end()) && cache[i+1]) {
            if (dfsWordBreak(s, dict, i+1, cache, temp, result))
                cache[start] = true;
        }
    }
    return cache[start];
}

vector<string> wordBreak(string s, unordered_set<string> &dict) {
    int l = s.size();
    vector<string> result;
    if (l == 0) {
        if (dict.find(s) != dict.end()) {
            result.push_back(s);
        }
        return result;
    }

    vector<bool> cache(l+1, true);
    string curStrings = "";
    dfsWordBreak(s, dict, 0, cache, curStrings, result);
    return result;
    
}

int main(int argc ,char** argv)
{
    string s = "catsanddog";
    unordered_set<string> dict = {
        "cat", "cats", "and", "sand", "dog"
    } ;
    vector<string> result = wordBreak(s, dict);
    for (int i = 0; i < result.size(); ++i)
        cout << result[i] << endl;

    return 0;
}
