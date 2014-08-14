#include <iostream>
#include <unordered_map>
using namespace std;

string intToRoman(int num) {
        int base = 1;
        string result = "";
        unordered_map<int, char> symmap {{1, 'I'} , {5, 'V'}, {10, 'X'}
        , {50, 'L'}, {100, 'C'}, {500, 'D'}, {1000, 'M'}};
        
        int curNum = 0;
        for (; num > 0; num -= curNum, base *= 10)
        {
            curNum = num % (base*10);
            if (curNum < base*4) {
                for (int i = curNum / base; i > 0; --i)
                {
                    result = symmap[base] + result;
                }
            } else if (curNum == base*4) {
                result = symmap[base*5] + result;
                result = symmap[base] + result;
            } else if (curNum < base*9) {
                for (int i = curNum/base - 5; i > 0; --i)
                    result = symmap[base] + result;
                result = symmap[base*5] + result;
            } else if (curNum == base*9) {
                result = symmap[base*10] + result;
                result = symmap[base] + result;
            }
        }
        
        return result;
    }

int main(int argc, char** argv)
{
    int test = 2014;
    cout << intToRoman(test) << endl;
    return 0;
}
