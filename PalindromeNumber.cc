#include <iostream>
#include <climits>
#include <cstdlib>
using namespace std;

//Use the reverse function in the solution of Reverse Integer problem
//O(log10(n)) time and O(1) space
int reverse(int x)
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

bool isPalindrome0(int x) {
    //Assuming all negative integers are not palindromic
    return (x >= 0) && (reverse(x) == x);

}

//Traditional way
//O(log(10)n) time and O(1) space
bool isPalindrome(int x) {
    if (x < 0)
        return false;

    int d = 1;

    //Be careful about overflowing here!
    while (d <= x/10) {
        d *= 10;
    }

    while(x > 0) {
        int leftDig = x / d;
        int rightDig = x % 10;
        if (leftDig != rightDig)
            return false;
        x = x % d;
        x = x / 10;
        d /= 100;
    }
    return true;
}

int main(int argc, char** argv)
{
    int x = atoi(argv[1]);
    
    cout << x << ": " << isPalindrome(x) << endl;
    return 0;
}
