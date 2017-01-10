#include <cstring>
#include <iostream>
using namespace std;

//Recursive solution . TLE
/*bool isMatch(const char *s, const char *p) {
        
        if (!*p)
            return !*s;
            
        if (((*s) && (*p == '?')) || (*s == *p))
            return isMatch(s+1, p+1);
            
        if (*p == '*') {
            do {
                if (isMatch(s, p+1))
                    return true;
            } while(*s++);
        } else
            return false;
        
}*/

//DP, TLE. O(Ns * Np) time, O(Ns) space
    bool isMatch1(const char *s, const char *p) {
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

//DP, TLE. O(Ns * Np) time, O(Np) space
 bool isMatch2(const char *s, const char *p) {
        int ns = strlen(s);
        int np = strlen(p);
        
        bool* prev = new bool[np+1];
        bool* cur = new bool[np+1];
        
        for (int i = ns; i >= 0; --i)
        {
            cur[np] = (i == ns) ? true : false;
            for (int j = np-1; j >= 0; --j)
            {
                if ((i < ns) && ((p[j] == '?') || (s[i] == p[j])))
                    cur[j] = prev[j+1];
                else if (p[j] == '*') {
                    cur[j] = cur[j+1];
                    if (i < ns)
                        cur[j] = cur[j] || prev[j];
                }
                else
                    cur[j] = false;
            }
            
            swap(prev, cur);
        }
        
        bool result = prev[0];
        delete[] prev;
        delete[] cur;
        return result;
    }

//DP TLE O(ns) space
bool isMatch3(const char *s, const char *p) {
    int ns = strlen(s);
    int np = strlen(p);
    vector<bool> cache(ns+1, false);
    cache[ns] = true;
    for (int i = np-1; i >= 0; --i)
    {
        bool prevOld = true;
        for (int j = ns; j >= 0; --j)
        {
            bool curOld = cache[j];
            if (p[i] == '*') {
                cache[j] = curOld || ((j < ns) && cache[j+1]);
            } else {
                cache[j] = (j < ns) && prevOld && ((p[i] == '?') || (p[i] == s[j]));
            }
            prevOld = curOld;
        }
    }
    
    return cache[0];
}

// O(Ns*Np) time and O(1) space
// This is essentially a backtracking algorithm.
// The key is that when you backtrack, you go to the last star positions, not
// the first one. Since the strings before the last star are already matched,
// and also the matched substring of s is the shortest one, so you should start
// from the next position after that.
// Worst case is when for each char in s, s pointer always marches forward and then
// go back. Since for each positin in s, the longest distance pointer s can go
// is equal to Np, the worst time complexity is O(Ns * Np)
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
    cout << isMatch("aaaa", "aa*") << endl; 
    return 0;
}
