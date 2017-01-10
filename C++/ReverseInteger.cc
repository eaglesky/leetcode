#include <climits>
#include <cstdlib>
#include <iostream>
using namespace std;

int reverse0(int x)
{
    int ret = 0;
    while(x != 0) {
        int dig = x % 10;
        x = x / 10;
        ret = ret*10 + dig;

        if ((ret > INT_MAX/10) || ((ret == INT_MAX/10) && (dig > 7)))
            break;
    }
    return  ret;
}

// Solution to the updated problem
int reverse(int x) {
    int result = 0;
    
    for (; x != 0; x /= 10)
    {
        int curDig = x % 10;
        
        if ((abs(result) > INT_MAX / 10) || ((abs(result) == INT_MAX / 10) && ((curDig > 7) || (curDig < -8))))
            return 0;
        result = result * 10 + curDig;
    }
    
    return result;
}

int main(int argc, char** argv)
{

    int x = atoi(argv[1]);
    cout << reverse(x) << endl;

    cout << INT_MIN << ", " << INT_MAX << endl;
    cout << -1041 % 10 << endl;
    return 0;
}
