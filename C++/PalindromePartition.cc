#include <iostream>
#include <vector>
using namespace std;

bool isValidPalindrome(string& s)
{
    for (int l = 0, r = s.size()-1; l < r; l++, r--)
    {
        if (s[l] != s[r])
            return false;
    }
    return true;
}

// Basic DFS
void partitionRec(string& s, int start, vector<string>& curPar, vector<vector<string> >& result, vector<vector<bool> >& valid)
{
    int len = s.size();
    if (start >= len) {
        result.push_back(curPar);
    }

    for (int i = start; i < len; ++i)
    {
        if (valid[start][i]) {
            curPar.push_back(s.substr(start, i-start+1));
            partitionRec(s, i+1, curPar, result, valid);
            curPar.pop_back();
        }
    }
}

vector<vector<string> > partition(string s) {
    vector<vector<string> > result;
    vector<string> curPartition;
    const int len = s.size();
    vector<vector<bool> > isPalin(len, vector<bool>(len, false));
    for (int i = len - 1; i >= 0; --i)
    {
        for (int j = i; j < len; ++j)
        {
            isPalin[i][j] = (s[i] == s[j]) && ((j - i < 2) || isPalin[i+1][j-1]);
        }
    }

    partitionRec(s, 0, curPartition, result, isPalin);
    return result;
        
}

//DP solution.
vector<vector<string> > partition0(string s) {
    const int len = s.size();
    vector<vector<string> > subPalins[len+1] ;
    subPalins[0] = vector<vector<string> >();
    subPalins[0].push_back(vector<string>());
    bool isPalin[len][len];

    for (int i = len - 1; i >= 0; --i)
    {
        for (int j = i; j < len; ++j)
        {
            isPalin[i][j] = (s[i] == s[j]) && ((j - i < 2) || isPalin[i+1][j-1]);
        }
    }

    for (int i = 1; i <= len; ++i)
    {
        subPalins[i] = vector<vector<string> >();
        for (int j = 0; j < i; ++j)
        {
            string rightStr = s.substr(j, i-j);
            if (isPalin[j][i-1]) {
                vector<vector<string> > prePartition = subPalins[j];
                for (int t = 0; t < prePartition.size(); ++t)
                {
                    prePartition[t].push_back(rightStr);
                    subPalins[i].push_back(prePartition[t]);
                }
            }
        }
    }
    return subPalins[len];
}

int main(int argc, char** argv)
{
    string test = "aab";
    vector<vector<string> > result = partition(test);
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
