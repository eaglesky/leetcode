#include <iostream>
#include <vector>
using namespace std;

//DP solution
//O(n^4) time and O(n^3) space
bool isScramble0(string s1, string s2) {
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

//Improved implementation
bool isScramble(string s1, string s2) {
    int n1 = s1.size();
    int n2 = s2.size();
    if (n1 != n2)
        return false;
    
    vector<vector<vector<bool> > > cache(n1+1, vector<vector<bool> >(n1, vector<bool>(n1, false)));
    
    for (int l = 1; l <= n1; ++l)
    {
        for (int i = 0; i+l <= n1; ++i)
        {
            for (int j = 0; j+l <= n1; ++j)
            {
                if (l == 1)
                    cache[l][i][j] = (s1[i] == s2[j]);
                    
                for (int subl = 1; subl < l; ++subl)
                {
                    if ((cache[subl][i][j] && cache[l-subl][i+subl][j+subl])
                    || (cache[subl][i][j+l-subl] && cache[l-subl][i+subl][j])) {
                        cache[l][i][j] = true;
                        break;
                    }
                }
            }
        }
    }
    
    return (n1 == 0) ? true : cache[n1][0][0];
}


int main(int argc, char** argv)
{
    string s1 = "great";
    string s2 = "rgtae";
    cout << isScramble(s1, s2) << endl; //Should be true
    return 0;
}

