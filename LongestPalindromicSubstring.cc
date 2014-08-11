#include <iostream>
using namespace std;

int expand(string s, int& start, int& end)
    {
        int slen = s.size();
        while ((start >= 0) && (end < slen) && (s[start] == s[end])) {
            start--;
            end++;
        }
        start++;
        end--;
        return end - start + 1;
    }
   
// O(N^2) time, O(1) space
    string longestPalindrome0(string s) {
        int slen = s.size();
        int maxLen = 0;
        int maxStart = 0;
        for (int i = 0; i < slen; ++i)
        {
            int start = i;
            int end = i;
            int curLen = expand(s, start, end);
            if (curLen > maxLen) {
                maxStart = start;
                maxLen = curLen;
            }
            start = i;
            end = i+1;
            curLen = expand(s, start, end);
            if (curLen > maxLen) {
                maxStart = start;
                maxLen = curLen;
            }
        }
        return s.substr(maxStart, maxLen);
    }

//Manacher's Algorithm
//O(N) time, O(N) space
 string longestPalindrome(string s) {
        int sLen = s.size();
        string  T = "";
        for (int i = 0; i < sLen; ++i)
        {
            T += '#';
            T += s[i];
        }
        T += '#';
        int tLen = T.size();
        int* P = new int[tLen];
        
        int C = 0;
        int R = 0;
        P[0] = 0;
        for (int i = 1; i < tLen; ++i)
        {
            int il = 2*C - i;
            if ((il <= 0) || (P[il] >= R - i)) {
              
                int end = (R > i) ? (R) : (i);
                int start = 2*i - end;
                P[i] = expand(T, start, end) / 2;
                C = i;
                R = end;
            } else {
                P[i] = P[il];
            }
        }
        
        int maxi = 0;
        for (int i = 0; i < tLen; ++i)
        {
            if (P[i] > P[maxi])
                maxi = i;
        }
        
        delete[] P;
        return s.substr((maxi - P[maxi] + 1)/2, P[maxi]);
    }

int main(int argc, char** argv)
{
    string test = "ccc";
    cout << longestPalindrome(test) << endl;
    return 0;
}
