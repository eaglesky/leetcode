#include <iostream>
#include <vector>
using namespace std;

//DP solution, O(n^2) time and O(n^2) space
int minCut0(string s) {
    int len = s.size();
    if (len == 0)
        return 0;

    vector<vector<bool> > isPalin(len, vector<bool>(len, false));
    for (int i = len - 1; i >= 0; --i)
        for (int j = i; j < len; ++j)
            isPalin[i][j] = (s[i] == s[j]) && ((j - i < 2) || isPalin[i+1][j-1]);

    vector<int> subMins(len);
    for (int i = 0; i < len; ++i)
    {
        subMins[i] = i;
    }

    for (int i = 1; i < len; ++i)
    {
        for (int j = 0; j <= i; ++j)
        {
            if (isPalin[j][i]) {
                if (j == 0) {
                    subMins[i] = 0;
                    break;
                } else if (subMins[j-1] + 1 < subMins[i])
                    subMins[i] = subMins[j-1] + 1;
            } 
        }
    }

    return subMins[len-1];
}

//Improved DP solution. O(n^2) time and space
int minCut1(string s) {
    int len = s.size();
    if (len == 0)
        return 0;
    
    vector<int> subMins(len+1);
    vector<vector<bool> > isPalin(len, vector<bool>(len, false));
    for (int i = 0; i <= len; ++i)
        subMins[i] = len - i - 1; //Min cut of sub-string from i to the end(included)

    for (int i = len - 1; i >= 0; --i)
        for (int j = i; j < len; ++j)
        {
            if ((s[i] == s[j]) && ((j-i < 2) || (isPalin[i+1][j-1]))) {
                isPalin[i][j] = true;
                if (subMins[j+1]+1 < subMins[i])
                    subMins[i] = subMins[j+1]+1;
            }
        }
    return subMins[0];
}

//Another DP solution. O(n^2) time and O(n) space!
int minCut(string s) {
    int len = s.size();
    if (len == 0)
        return 0;
    
    vector<int> subMins(len+1);
    for (int i = 0; i <= len; ++i)
        subMins[i] = i - 1;
    
    for (int i = 0; i < len; ++i)
    {
        //Check possible palindromes of odd length
        for (int j = 0; (i - j >= 0) && (i + j < len) && (s[i-j] == s[i+j]); ++j)
            subMins[i+j+1] = min(subMins[i+j+1], subMins[i-j] + 1);

        //Check possible palindromes of even length
        for (int j = 0; (i - j >= 0) && (i + 1 + j < len) && (s[i-j] == s[i+j+1]); ++j)
            subMins[i+j+2] = min(subMins[i+j+2], subMins[i-j] + 1);
    }
    return subMins[len];
}

int main(int argc, char** argv)
{
    string test = "efe";
    cout << minCut(test) << endl;
    return 0;
}
