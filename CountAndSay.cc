#include <iostream>
using namespace std;

string countAndSay0(int n) {
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

//The question is asking what the nth string is in the sequence starting with
//"1"
 string countAndSay(int n) {
        
        string str = "1";
        for (int i = 1; i < n; ++i)
        {
            string nextStr = "";
            for (int j = 0; j < str.size();)
            {
                int t;
                for (t = j+1; (t < str.size()) && (str[t] == str[j]); ++t);
                char countChar = (t-j) + '0';
                
                nextStr += countChar;
                nextStr += str[j];
                j = t;
            }
            str = nextStr;
        }
        
        return str;
    }

int main(int argc, char** argv)
{
    for (int i = 0; i < 5; ++i)
    {
        cout << countAndSay(i+1) << endl;
    }
    return 0;
}
