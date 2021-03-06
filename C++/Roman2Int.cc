#include <iostream>
#include <unordered_map>
using namespace std;

int romanToInt0(string s)
{
    unordered_map<string, int> symbols = {
        {"M", 1000}, {"CM", 900}, {"D", 500},
        {"CD", 400}, {"C", 100}, {"XC", 90},
        {"L", 50}, {"XL", 40}, {"X", 10}, 
        {"IX", 9}, {"V", 5}, {"IV", 4}, 
        {"I", 1}
    };

    int result = 0;
    for (int i = 0; i < s.size(); )
    {
        if (i < s.size() - 1) {
            auto it = symbols.find(s.substr(i, 2));
            if (it != symbols.end()) {
                result += it->second;
                i += 2;
                continue;
            }
        }
        auto it = symbols.find(s.substr(i, 1));
        result += it->second;
        i++;
    }
    return result;
}

//Can be imroved to O(1) space by replacing symmap with switch statements
    int romanToInt1(string s) {
    
        unordered_map<char, int> symmap= {
            {'I', 1},
            {'V', 5},
            {'X', 10},
            {'L', 50},
            {'C', 100},
            {'D', 500},
            {'M', 1000}
        }   ;
        
        int result = 0;
        
        for (int i = 0; i < s.size(); ++i)
        {
            if ((i > 0) && (symmap[s[i]] > symmap[s[i-1]])) {
                result = result + symmap[s[i]] - 2*symmap[s[i-1]];
            } else
                result += symmap[s[i]];
        }
        
        return result;
    }

//Best solution with O(1) space
int map(char c)
{
    switch(c) {
        case 'I': return 1;
        case 'V': return 5;
        case 'X': return 10;
        case 'L': return 50;
        case 'C': return 100;
        case 'D': return 500;
        case 'M': return 1000;
        default: return 0;
    }
}

int romanToInt(string s) {
    int n = s.size();
    
    int curMax = 0;
    int result = 0;
    for (int i = n-1; i >= 0; --i)
    {
        if (map(s[i]) >= curMax) {
            result += map(s[i]);
            curMax = map(s[i]);
        } else {
            result -= map(s[i]);
        }
    }
    
    return result;
}

int main(int argc, char** argv)
{
    string test = "MMXIV";
    cout << romanToInt(test) << endl;
    return 0;
}
