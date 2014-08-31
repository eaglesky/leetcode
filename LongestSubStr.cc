#include <iostream>
#include <unordered_map>
using namespace std;

//O(n) time and O(n) space
int lengthOfLongestSubstring(string s) {
    unordered_map<char, int> map;

    //Maximum length
    int maxLen = 0;

    //Maximum length of substring that ends with previous character
    int prevLen = 0;

    for (int i = 0; i < s.size(); ++i)
    {
        auto it = map.find(s[i]);

        // Previous position of s[i]
        int prev = -1;
        if (it != map.end())
            prev = it->second;

        //Maximum length of substring that ends with current character
        int curLen = min(prevLen+1, i-prev);

        maxLen = max(maxLen, curLen);
        prevLen = curLen;
        map[s[i]] = i;
    }
    return maxLen;
}

int main(int argc, char** argv)
{
//    string str1 = "abcabcbb";
 //   string str2 = "bbbb";
    string str3 = "wlrbbmqbhcdarzowkkyhiddqscdxrjmowfrxsjybldbefsarcbynecdyggxxpklorellnmpapqfwkhopkmco";
 //   cout << str1 << ": " << lengthOfLongestSubstring(str1) << endl;
 //   cout << str2 << ": " << lengthOfLongestSubstring(str2) << endl;
    cout << str3 << ": " << lengthOfLongestSubstring(str3) << endl;
    return 0;
}


