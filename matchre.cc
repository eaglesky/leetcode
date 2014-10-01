#include <iostream>
#include <cstring>
using namespace std;

// Exponential time and O(ns + np) space
 bool isMatch0(const char *s, const char *p) {
        
        if (!*p)
            return !*s;
            
        if (*(p+1) == '*') {
            while (1) {
                if (isMatch(s, p+2))
                    return true;
                
                if (((*s) && (*p == '.')) || (*s == *p))
                    s++;
                else
                    break;
            }
            return false;
        } else if (((*s) && (*p == '.')) || (*s == *p))
            return isMatch(s+1, p+1);
        else
            return false;
         
    }


 // DP solution.  O(N^2) time and O(N) space
    bool isMatch1(const char *s, const char *p) {

        int sLen = strlen(s);

        int pLen = strlen(p);

        

        bool* dp0 = new bool[sLen+1];

        bool* dp1 = new bool[sLen+1];

        fill_n(dp0, sLen+1, false);

        fill_n(dp1, sLen+1, false);

        dp1[0] = true;

        for (int ip = 1; ip <= pLen; ++ip)

        {

            bool* dpcur = new bool[sLen+1];

            for (int is = 0; is <= sLen; ++is)

            {

                if ((p[ip-1] != '*')) {

                    dpcur[is] = (is > 0) && ((s[is-1] == p[ip-1]) || (p[ip-1] == '.')) && dp1[is-1];

                } else {

                    dpcur[is] = dp0[is] || ((is > 0) && ((s[is-1] == p[ip-2]) || (p[ip-2] == '.')) && (dpcur[is-1]));

                }

            }

            delete[] dp0;

            dp0 = dp1;

            dp1 = dpcur;

        }

        

        bool ret = dp1[sLen];

        

        delete[] dp0;

        delete[] dp1;

        

        return ret;

    }

//DP solution improved. O(ns*np) time and O(np) space
bool isMatch(const char *s, const char *p) {
    int ns = strlen(s);
    int np = strlen(p);
    
    bool* prev = new bool[np+1];
    bool* next = new bool[np+1];
    fill_n(prev, np+1, false);
    fill_n(next, np+1, false);
  
    for (int i = ns; i >= 0; --i)
    {
        next[np] = (i == ns) ? true : false;

        for (int j = np-1; j >= 0; --j)
        {
            
            if (p[j] != '*') {
                if ((j < np-1) && (p[j+1] == '*')) {
                    next[j] = next[j+2];
                    if ((i < ns) && ((p[j] == '.') || (s[i] == p[j])))
                        next[j]  = next[j] || prev[j];
                    
                } else if ((i < ns) && ((p[j] == '.') || (s[i] == p[j])))
                    next[j] = prev[j+1];
                else
                    next[j] = false;
                
            }
            
        }
        swap(prev, next);
    }
    
    bool result = prev[0];
    delete[] prev;
    delete[] next;
    return result;        
}


int main(int argc, char** argv)
{
    cout << isMatch("aaba", "ab*a*c*a") << endl;
    return 0;
}
