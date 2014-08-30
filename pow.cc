#include <climits>
#include <cmath>
#include <iostream>
#include <vector>
using namespace std;

//Recursive solution (n >= 0)
//O(logn) time and O(logn) space
double power0(double x, int n) {
    if (n == 0)
        return 1;
    double half = power0(x, n/2);
    if (n & 1)
        return half*half*x;
    else
        return half*half;

}

// Iterative solution (n >= 0)
// Stack simulation
// O(lognn) time and O(logn) space
double power(double x, int n)
{
    vector<double> stack;
    while( n > 0 ) {
        if (n & 1)
            stack.push_back(x);
        else
            stack.push_back(1);
        n = n >> 1;
    }
    double result = 1;
    while (!stack.empty()) {
        double cur = stack.back();
        stack.pop_back();
        result = cur*(result*result);
    }
    return result;
}

// Iterative solution (n >= 0)
// O(logn) time and O(1) space
double power1(double x, int n) {
    double result = 1;
    double cur = x;

    while (n > 0) {
       if (n & 1) 
           result *= cur;
       n = n >> 1;
       cur = cur * cur;
    }
    return result;
}

double mypow(double x, int n) {
    // Avoid binary overflow when n == INT_MIN
    if (n < 0)
        return 1 / (x * power(x, -(n+1))); 
    else
        return power(x, n);
}


int main(int argc, char** argv)
{
    double x = 2 ;
    int n = -3;
    cout << mypow(x, n) << endl;
    cout << pow(x, n) << endl;
    return 0;
}
