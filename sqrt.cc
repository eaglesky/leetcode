#include <cstdlib>
#include <cmath>
#include <iostream>
using namespace std;

int mysqrt0(int x) {
    int start = 1;
    int end = x;
    if (x == 0)
        return 0;

    while (end - start > 1) {
        int mid = start + (end - start) / 2;
        if (x / mid == mid)
            return mid;
        else if (x / mid > mid) {
            start = mid;
        } else
            end = mid;
    }
    return start;
}

  int mysqrt(int x) {
        
        if (x < 0)
            return -1;
        else if (x == 0)
            return 0;
        
        int left = 1;
        int right = x;
        while (left < right - 1) {
            int mid = left + ((right - left) >> 1);
            if (mid == x / mid)
                return mid;
            else if (mid < x / mid) 
                left = mid;
            else
                right = mid - 1;
        }
        
        return (right <= x / right) ? right : left;
    }

int main(int argc, char** argv)
{
    int x = atoi(argv[1]);

    cout << "mysqrt(" << x << ") = " << mysqrt(x) << endl;
    cout << "sqrt(" << x << ") = " << sqrt(x) << endl;

    return 0;
}
