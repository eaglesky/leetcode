#include <iostream>
#include <cstring>
using namespace std;

// Exponential time and O(n) space
/*bool isMatch0(const char *s, const char *p) {

        

        if ((!*p) && (!*s))

            return true;

        else if (*p) {

            if (*(p+1) != '*') {

                if (((*p == '.') && (*s)) || (*s == *p))

                    return isMatch(s+1, p+1);

                else

                    return false;

            } else {



                while (((*s) && (*p == '.')) || (*p == *s))  {

                    if (isMatch(s, p+2))

                        return true;

                    s++;

                }

                return isMatch(s, p+2);

            }

        } else

            return false;

    }
    */
 // DP solution.  O(N^2) time and O(N) space
    bool isMatch(const char *s, const char *p) {
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

int main(int argc, char** argv)
{
    cout << isMatch("aaba", "ab*a*c*a") << endl;
    return 0;
}
