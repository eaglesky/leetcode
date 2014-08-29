#include <iostream>
#include <vector>
using namespace std;

//Recursive solution using DFS. More cutting-branches operations can be added
void dfsRestoreIP(string& s, int start, vector<string>& curCombine, vector<string>& result)
{
    if ((start >= s.size()) && (curCombine.size() == 4)) {
        string curResult = "";
        for (int i = 0; i < 3; ++i)
        {
            curResult += curCombine[i];
            curResult += ".";
        }
        curResult += curCombine[3];
        result.push_back(curResult);
        return;
    }

    if (curCombine.size() >= 4)
        return;

    int num = 0;
    for (int i = 0; i < 3; ++i)
    {
        if (start + i >= s.size())
            break;
        string curStr = s.substr(start, i+1);
        num = num*10 + (s[start+i]-'0');
        if ((num >= 0) && (num <= 255)) {
            curCombine.push_back(curStr);
            dfsRestoreIP(s, start+i+1, curCombine, result);
            curCombine.pop_back();
        }
        if (num == 0)
            break;
    }
}

vector<string> restoreIpAddresses0(string s) {
    vector<string> combine;
    vector<string> result;
    dfsRestoreIP(s, 0, combine, result);
    return result;
}

//An iterative solution using three loops exists
bool isValid(string s) {
    if (s.size() > 1 && s[0] == '0')
        return false;
    if (stoi(s) <= 255 && stoi(s) >= 0)
        return true;
    else
        return false;
}

vector<string> restoreIpAddresses(string s)  {
    vector<string> res;
    if (s.size() > 12 || s.size() < 4)
         return res;

    for (int i=1; i<4; i++) {
        string first = s.substr(0, i);
        if (!isValid(first))
            continue;
        for (int j=1; i+j < s.size() && j<4; j++) {
            string second = s.substr(i, j);
            if (!isValid(second))
                continue;
            for (int k=1; i+j+k < s.size() && k<4; k++) {
                string third = s.substr(i+j, k);
                string fourth = s.substr(i+j+k);
                if (isValid(third) && isValid(fourth)) {
                    string temp = first+"."+second+"."+third+"."+fourth;
                    res.push_back(temp);
                }
            }
        }
    }

    return res;
}

int main(int argc, char** argv)
{
    string test = "25525511135";
    vector<string> result = restoreIpAddresses(test);
    for (int i = 0; i < result.size(); ++i)
        cout << result[i] << endl;
    return 0;
}
