#include <iostream>
#include <unordered_set>
#include <vector>
using namespace std;

// DP solution
// O(n^2) time and O(n) space
bool wordBreak(string s, unordered_set<string> &dict) {
    int l = s.size();
    if (l == 0)
        return (dict.find(s) != dict.end());

    vector<bool> f(l, false);
    for (int i = 0; i < l; ++i)
    {
        for (int j = i; j >= 0; --j)
        {
           if ((dict.find(s.substr(j, i-j+1)) != dict.end()) && ((j < 1) || f[j-1])) {
                f[i] = true;
                break;
            }
        }
    }
    return f[l-1];
}

// Another solution using BFS
// https://oj.leetcode.com/discuss/8479/a-solution-using-bfs
// Same time and space complexity


int main(int argc,  char** argv)
{
    string s = "leetcode";
    unordered_set<string> dict = {"leet", "code"};
    cout << wordBreak(s, dict) << endl;
    return 0;
}
