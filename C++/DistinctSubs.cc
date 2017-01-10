#include <iostream>
#include <vector>
using namespace std;

//DP solution
//O(ns*nt) time and O(nt) space
int numDistinct(string S, string T) {
    int ns = S.size();
    int nt = T.size();
    vector<int> d(nt+1, 0);

    for (int i = 0; i <= ns; ++i)
    {
        for (int j = min(i, nt); j >= 1; --j)
        {
            if (S[i-1] == T[j-1])
                d[j] += d[j-1];

        }
        d[0] = 1;
    }
    return d[nt];
}     

int main(int argc, char** argv)
{
    //string S = "rabbbit";
    //string T = "rabbit";
    string S = "babbaab";
    string T = "bbbb";
    cout << numDistinct(S, T) << endl;
    return 0;
}

