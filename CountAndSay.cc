#include <iostream>
using namespace std;

string countAndSay(int n) {
    string result = "1";
    for (int i = 1; i < n; ++i)
    {
        string next = "";
        int lastPos = 0;
        char count;
        int j;
        for (j = 0; j <= result.size(); ++j)
        {
            if (j > 0 && ((j == result.size()) || (result[j] != result[j-1]))) {
                count = (j - lastPos) + '0';
                next += count;
                next += result[lastPos];
                lastPos = j;
            }
        }
      /*  if (j > 0) {
            count = (j - lastPos) + '0';
            next += count;
            next += result[lastPos];
        }*/
        result = next;
    }

    return result;
}

int main(int argc, char** argv)
{
    for (int i = 0; i < 5; ++i)
    {
        cout << countAndSay(i+1) << endl;
    }
    return 0;
}
