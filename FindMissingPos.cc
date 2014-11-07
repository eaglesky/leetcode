#include <algorithm>
#include <iostream>
using namespace std;

//Book has a more concise solution!
int firstMissingPositive0(int A[], int n) {
    for (int i = 0; i < n; ++i)
    {
        if ((A[i] < 1) || (A[i] > n)) {
            A[i] = 0;
        } else if (A[i] != (i+1)) {
            while ((A[A[i]-1] >= 1) && (A[A[i]-1] <= n)
                    && (A[A[i]-1] != A[i])){
                swap(A[i], A[A[i]-1]);
            }
            A[A[i]-1] = A[i];

            if (A[i] != i+1)
                A[i] = 0;
        }
    }
    for (int i = 0; i < n; ++i)
    {
        if (A[i] == 0)
            return i+1;
    }
    return n+1;
}

// More concise solution
int firstMissingPositive(int A[], int n) {
    for (int i = 0; i < n;)
    {
        if ((A[i] >= 1) && (A[i] <= n) && (A[A[i]-1] != A[i])) {
            swap(A[i], A[A[i]-1]);
        } else
            ++i;
    }
    
    
    for (int i = 0; i < n; ++i)
    {
        if (A[i] != (i+1))
            return i+1;
    }
    
    return n+1;
}

int main(int argc, char** argv)
{
    int test[] = {2, 1};
    cout << firstMissingPositive(test, sizeof(test)/sizeof(int)) << endl;
    return 0;
}
