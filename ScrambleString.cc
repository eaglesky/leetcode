#include <iostream>
#include <vector>
using namespace std;

//DP solution
//O(n^4) time and O(n^3) space
bool isScramble(string s1, string s2) {
    if (s1.size() != s2.size())
        return false;

    int n = s1.size();

    vector<vector<vector<bool> > > f(n+1, vector<vector<bool> >(n, vector<bool>(n, false)));
   
    //These two lines are much faster than the above
  /*  bool f[n + 1][n][n];
    fill_n(&f[0][0][0], (n + 1) * n * n, false);*/

    for (int i = 0; i < n; ++i)
        for (int j = 0; j < n; ++j)
            f[1][i][j] = (s1[i] == s2[j]);

    for (int l = 2; l <= n; ++l)
    {
        for (int i = 0; i+l <= n; ++i)
        {
            for (int j = 0; j+l <= n; ++j)
            {
                for (int t = 1; t < l; ++t)
                {
                    if ((f[t][i][j] && f[l-t][i+t][j+t]) ||
                            (f[t][i][j+l-t] && f[l-t][i+t][j])) {
                        
                        f[l][i][j] = true;
                        break;
                    }

                }
            }
        }
    }

    return f[n][0][0];
}

int main(int argc, char** argv)
{
    string s1 = "great";
    string s2 = "rgtae";
    cout << isScramble(s1, s2) << endl; //Should be true
    return 0;
}

