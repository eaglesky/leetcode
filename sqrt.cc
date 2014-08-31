#include <cstdlib>
#include <cmath>
#include <iostream>
using namespace std;

int mysqrt(int x) {
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

int main(int argc, char** argv)
{
    int x = atoi(argv[1]);

    cout << "mysqrt(" << x << ") = " << mysqrt(x) << endl;
    cout << "sqrt(" << x << ") = " << sqrt(x) << endl;

    return 0;
}
