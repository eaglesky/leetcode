#include <iostream>
using namespace std;

int removeElement(int A[], int n, int elem) {
    int i = -1;
    int j = 0;
    for (; j < n; ++j)
    {
        if (A[j] != elem)
            A[++i] = A[j];
    }
    return i+1;
}

int main(int argc, char** argv)
{
    return 0;
}
