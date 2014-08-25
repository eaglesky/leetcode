#include <algorithm>
#include <iostream>
using namespace std;



void sortColors(int A[], int n) {
    int ir = 0;
    int ib = n - 1;
    int i = 0;
    while(i <= ib)
    {
        if (A[i] == 0) {
            swap(A[i++], A[ir++]);
        } else if (A[i] == 2) {
            swap(A[i], A[ib--]);
        } else
            i++;
    }
}

int main(int argc, char** argv)
{
    return 0;
}
