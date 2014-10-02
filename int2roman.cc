#include <iostream>
#include <unordered_map>
using namespace std;

string intToRoman0(int num) {
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

  string intToRoman(int num) {
        unordered_map<int, char> symmap {
            {1, 'I'},
            {5, 'V'},
            {10, 'X'},
            {50, 'L'},
            {100, 'C'},
            {500, 'D'},
            {1000, 'M'}
        };
        
        string result = "";
        for (int i = 1; num > 0; i *= 10, num /= 10)
        {
            string cur = "";
            int digit = num % 10;
            if (digit <= 3) {
                for (int j = 0; j < digit; ++j)
                    cur += symmap[i];
            } else if (digit == 4) {
                cur += symmap[i];
                cur += symmap[i*5];
            } else if (digit < 9) {
                cur += symmap[i*5];
                for (int j = 0; j < digit-5; ++j)
                    cur += symmap[i];
            } else if (digit == 9) {
                cur += symmap[i];
                cur += symmap[i*10];
            }
            result = cur + result;
           
        }
        return result; 
    }

int main(int argc, char** argv)
{
    int test = 2014;
    cout << intToRoman(test) << endl;
    return 0;
}
