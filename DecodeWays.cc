#include <iostream>
#include <vector>
using namespace std;

//DP solution
//O(n) time and O(1) space
int numDecodings(string s) {
    int n = s.size();

    int prev = 1;
    int prev2 = 1;
    int cur = 0;
    for (int i = 1; i <= n; ++i)
    {
        cur = 0;
        if (s[i-1] != '0')
            cur += prev;

        if (i >= 2) {
            int dig = stoi(s.substr(i-2, 2));
            if ((dig >= 10) && (dig <= 26))
                cur += prev2;

            prev2 = prev;
        }
        prev = cur;
    }

    return cur;
}

int main(int argc, char** argv)
{
    string test = "12";
    cout << numDecodings(test) << endl;

    return 0;
}
