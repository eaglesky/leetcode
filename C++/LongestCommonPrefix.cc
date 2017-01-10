#include <iostream>
#include <vector>
using namespace std;

string longestCommonPrefix(vector<string> &strs) {
    for (int i = 0;!strs.empty() ; ++i)
    {
        for (int j = 0; j < strs.size(); ++j)
        {
            if ((strs[j].size() <= i) || (strs[j][i] != strs[0][i]))
                return strs[0].substr(0, i);
        }
    }
    return "";
}

int main(int argc, char** argv)
{
    return 0;
}
