#include <iostream>
using namespace std;

int removeDuplicates(int A[], int n) {
        if (n == 0)   
            return 0;
        int i = 0;
        int j = 1;
        for (; j < n; ++j)
        {
            if (A[j] != A[j-1]) {
                A[++i] = A[j];
            }
        }
        return i+1;
    }

int main(int argc, char** argv)
{
    int test[] = {1, 1, 2};
    cout << removeDuplicates(test, sizeof(test)/sizeof(int)) << endl;
    return 0;
}
