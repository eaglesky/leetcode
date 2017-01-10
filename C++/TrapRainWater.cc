#include <iostream>
using namespace std;

//O(n) time and O(1) space
int trap(int A[], int n) {
    int maxBar = 0;
    for (int i = 1; i < n; ++i)
        maxBar = (A[i] > A[maxBar]) ? i : maxBar;

    int maxLeft = 0;
    int waterAmount = 0;
    for (int i = 0; i < maxBar; ++i)
    {
        if (A[i] < maxLeft) {
            waterAmount += maxLeft - A[i];
        } else
            maxLeft = A[i];
    }

    int maxRight = 0;
    for (int i = n-1; i > maxBar; --i)
    {
        if (A[i] < maxRight) {
            waterAmount += maxRight - A[i];
        } else
            maxRight = A[i];
    }

    return waterAmount;
                                
}

int main(int argc, char** argv)
{
    int test[] = {0,1,0,2,1,0,1,3,2,1,2,1};
    cout << trap(test, sizeof(test) / sizeof(int)) << endl;
    return 0;
}
