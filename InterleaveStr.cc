#include <iostream>
#include <vector>
using namespace std;

// DP solution.
// cache[i][j] = (s1[i-1] == s3[i+j-1]) && cache[i-1][j]
// || (s2[j-1] == s3[i+j-1]) && cache[i][j-1]
// cache[i][j] represents whether s3[0, i+j) is formed by the interleaving of
// s1[0, i) and s2[0, j)
// O(l1*l2) time and O(l2) space
bool isInterleave0(string s1, string s2, string s3) {
    int l1 = s1.size();
    int l2 = s2.size();
    int l3 = s3.size();
    if (l3 != l1 + l2)
        return false;

    vector<bool> cache(l2+1, false); 
    for (int i = 0; i <= l1; ++i)
    {
        for (int j = 0; j <= l2; ++j)
        {
            if ((i == 0) && (j == 0)) {
                cache[j] = true;
                continue;
            }
            
            bool flag1 = false;
            bool flag2 = false;

            if (i != 0)
                flag1 = (s1[i-1] == s3[i+j-1]) && cache[j];

            if (j != 0)
                flag2 = (s2[j-1] == s3[i+j-1]) && cache[j-1];
            cache[j] = flag1 || flag2;
        }
    }
    return cache[l2];

}

//Better implementation
bool isInterleave(string s1, string s2, string s3) {
    int n1 = s1.size();
    int n2 = s2.size();
    int n3 = s3.size();
    if (n3 != n1 + n2)
        return false;
    
    vector<bool> cache(n2+1, false);
    cache[0] = true;
    
    for (int i = 0; i <= n1; ++i)
    {
        for (int j = 0; j <= n2; ++j)
        {
            bool check1 = (j > 0) ? cache[j-1] && (s2[j-1] == s3[i+j-1]) : false;
            bool check2 = (i > 0) ? cache[j] && (s1[i-1] == s3[i+j-1]) : false;
            
            if (i == 0 && j == 0)
                cache[j] = true;
            else
                cache[j] = check1 || check2;
        }
    }
    
    return cache[n2];
}

int main(int argc, char** argv)
{
    string s1 = "aabcc";
    string s2 = "dbbca";
    string s31 = "aadbbcbcac"; // True
    string s32 = "aadbbbaccc"; // False

    cout << isInterleave(s1, s2, s31) << endl;
    cout << isInterleave(s1, s2, s32) << endl;
    return 0;
}
