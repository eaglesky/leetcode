#include <iostream>
#include <vector>
using namespace std;
//Iterative solutions are similar to the answers of gray code question

void dfs(string* map, string& digits, int pos, string& curStr, vector<string>& result)
{
    if (pos == digits.size()) {
        result.push_back(curStr);
        return;
    }

    string curSel = map[digits[pos]-'0'];
    for (int i = 0; i < curSel.size(); ++i)
    {
        curStr += curSel[i];
        dfs(map, digits, pos+1, curStr, result);
        curStr.pop_back();
    }
}

vector<string> letterCombinations0(string digits) {
    string map[10] = {" ", "",  "abc", "def", "ghi", "jkl", 
    "mno", "pqrs", "tuv", "wxyz"};
    vector<string> result;
    string curStr;
    dfs(map, digits, 0, curStr, result);
    return result;
}

// Return true if ids is increased to maximum
bool increase(int* ids, int* limits, int n)
{
    ids[n-1]++;
    int carry = 0;
    int i;
    for (i = n-1; i >= 0; --i)
    {
        ids[i] += carry ;
        carry = ids[i] / limits[i];
        ids[i] = ids[i] % limits[i];
        if (carry == 0)
            break;
    }
    return (i < 0) ? true : false; 
}

//My iterative solution
vector<string> letterCombinations1(string digits) {
    string map[10] = {" ", "",  "abc", "def", "ghi", "jkl", 
    "mno", "pqrs", "tuv", "wxyz"};
    vector<string> result;
    int n = digits.size();
    if (n == 0) {
        result.push_back("");
        return result;
    }
    int* ids = new int[n];
    int* limits = new int[n];
    fill_n(ids, n, 0);
    for (int i = 0; i < n; ++i)
    {
        limits[i] = map[digits[i]-'0'].size();
    }

    do {
        result.push_back("");
        for (int i = 0; i < n; ++i)
            result[result.size()-1] += map[digits[i]-'0'][ids[i]];
    } while (!increase(ids, limits, n));

    delete[] ids;
    delete[] limits;
}

//Iteration solution 2 using base conversion
vector<string> letterCombinations(string digits) {
    string map[10] = {" ", "",  "abc", "def", "ghi", "jkl", 
    "mno", "pqrs", "tuv", "wxyz"};
    vector<string> result;
    int totalNum = 1;
    int digNum = digits.size();
    for (int i = 0; i < digNum; ++i)
    {
        totalNum *= map[digits[i]-'0'].size();
    }

    //id = d0*b1*b2*...*bn-1 + d1*b2*b3*...*bn-1 + dn-3*bn-2*bn-1 + dn-2*bn-1 + dn-1
    for (int i = 0; i < totalNum; ++i)
    {
        string curStr;
        int id = i;
        for (int j = digNum-1; j >= 0; --j)
        {
            int c = digits[j] - '0';
            int base = map[c].size();
            int d = id % base;
            curStr = map[c][d] + curStr;
            id /= base;
        }
        result.push_back(curStr);
    }
    return result;
}

int main(int argc, char** argv)
{
    string test = argv[1];
    vector<string> result = letterCombinations(test);
    for (int i = 0; i < result.size(); ++i)
        cout << result[i] << endl;
    cout << "Total: " << result.size() << endl;
    return 0;
}
