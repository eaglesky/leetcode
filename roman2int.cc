#include <iostream>
#include <unordered_map>
using namespace std;

int romanToInt(string s)
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

int main(int argc, char** argv)
{
    string test = "MMXIV";
    cout << romanToInt(test) << endl;
    return 0;
}
