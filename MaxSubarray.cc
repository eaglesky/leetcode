#include <climits>
#include <iostream>

using namespace std;

//O(n) time and O(1) space
int maxSubArray(int A[], int n) {

    int maxSum = INT_MIN;
    int curSum = 0;
    if (n == 0)
        return 0;

    for (int i = 0; i < n; ++i)
    {
        if (curSum < 0)
            curSum = A[i];
        else
            curSum += A[i];

        if (curSum > maxSum)
            maxSum = curSum;
    }
    return maxSum;
}

int main(int argc, char** argv)
{
    int A[] = {-2, 1, -3, 4, -1, 2, 1, -5, 4};
    cout << maxSubArray(A, sizeof(A)/sizeof(int)) << endl;
    return 0;
}
