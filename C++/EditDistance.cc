#include <iostream>
#include <vector>
using namespace std;

//DP solution
//O(l1*l2) time and O(l2) space
int minDistance(string word1, string word2) {
    int l1 = word1.size();
    int l2 = word2.size();

    vector<int> f(l2+1, 0);
    for (int j = 1; j <= l2; ++j)
        f[j] = j;

    for (int i = 1; i <= l1; ++i)
    {
        int prev = i;
        for (int j = 1; j <= l2; ++j)
        {
            int cur;
            if (word1[i-1] == word2[j-1]) {
                cur = f[j-1];
            } else {
                cur = min(min(f[j-1], prev), f[j]) + 1;
            }

            f[j-1] = prev;
            prev = cur;
        }
        f[l2] = prev;
    }
    return f[l2];
}

int main(int argc, char** argv)
{
    return 0;
}
