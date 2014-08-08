#include <iostream>
#include <vector>
using namespace std;

void add(vector<int> &digits, int digit)
    {
        int base = 10;
        int carry = digit;
        for(auto it = digits.rbegin(); it != digits.rend(); it++)
        {
            int sum = *it +  carry;
            *it = sum % base;
            carry = sum / base;
        }
        
        if (carry > 0) {
            digits.insert(digits.begin(), carry);
        }
    }
    
    vector<int> plusOne(vector<int> &digits) {
        add(digits, 1);
        return digits;
    }

int main(int argc, char** argv)
{
    vector<int> test{1, 0};
    plusOne(test);
    for (int i = 0; i < test.size(); ++i)
        cout << test[i];
    cout << endl;

    return 0;
}
