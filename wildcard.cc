#include <cstring>
#include <iostream>
using namespace std;

//DP, TLE. O(Ns * Np) time, O(Ns) space
    bool isMatch0(const char *s, const char *p) {
        int sLen = strlen(s);
        int pLen = strlen(p);
        
       const char* tmp = p;
        int cnt = 0;
        while (*tmp) if (*(tmp++) != '*') cnt++;  
        if (cnt > sLen) return false;  
            
        bool* dp = new bool[sLen+1];
        
        fill_n(dp, sLen+1, false);
        dp[0] = true;
        
        for (int j = 1; j <= pLen; ++j)
        {
            if (p[j-1] != '*') {
                for (int i = sLen; i >= 0; --i)
                {
                    dp[i] = (i > 0) && ((s[i-1] == p[j-1])||(p[j-1] == '?')) && dp[i-1];
                }
            } else {
                int i;
                for (i = 0; (i <= sLen) && (!dp[i]); ++i) {}
                for (; i <= sLen; ++i)
                    dp[i] = true;
            }
        }
        
        bool ret = dp[sLen];
        delete[] dp;
        return ret;
    }

// O(Ns^2) time and O(1) space
 bool isMatch(const char *s, const char *p) {
        
        const char* preStar = NULL;
        const char* preS = s;
        while(*s) {
            if ((*s == *p) || (*p == '?')) {
                s++;
                p++;
            } else if (*p == '*') {
                preStar = p++;
                preS = s;
            } else if (preStar) {
                p = preStar + 1;
                s = ++preS;
            } else 
                return false;
        }
        
        while (*p == '*') p++;
        return !*p;
    }

int main(int argc, char** argv)
{
    cout << isMatch("aaaa", "aa*b") << endl; 
    return 0;
}
