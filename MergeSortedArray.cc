#include <iostream>
using namespace std;

void merge(int A[], int m, int B[], int n) {
    int ia = m - 1;
    int ib = n - 1;
    int i = m + n - 1;

    while ((ib >= 0)) {
        if ((ia >= 0) && (A[ia] > B[ib])) {
            A[i] = A[ia];
            ia--;
        } else {
            A[i] = B[ib];
            ib--;
        }
        i--;
    }
}

int main(int argc, char** argv)
{
    int testA[] = {1, 3, 5, 0};
    int testB[] = {7};
    int szA = sizeof(testA)/sizeof(int);
    int szB = sizeof(testB)/sizeof(int);
    cout << "Size of Array A: " << szA << endl;
    cout << "Size of Array B: " << szB << endl;
    merge(testA,  szA - szB, testB, szB);

    for (int i = 0; i < szA; ++i)
        cout << testA[i] << ", ";
    cout << endl;


    return 0;
}
