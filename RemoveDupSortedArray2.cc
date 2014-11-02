#include <iostream>
using namespace std;

int removeDuplicates0(int A[], int n) {

    if (n == 0)
        return 0;

    int cnt = 0;

    int i = 0;
    int j = 1;
    for (; j < n; ++j)
    {
        if (A[j] == A[i]) {
            cnt++;
        } else {
            cnt = 0;
        }

        if (cnt <= 1)
            A[++i] = A[j];
    }
    return i+1;
}

//Better implementation
int removeDuplicates1(int A[], int n) {
    int dupNum = 2;
    int start = 0;
    int dupCount = 1;
    if (n <= dupNum)
        return n;
    for (int i = 1; i < n; ++i)
    {
        if (A[start] != A[i] || (dupCount < dupNum)) {
            
            if (A[start] == A[i])
                dupCount++;
            else
                dupCount = 1;
                
            A[++start] = A[i];
        } 
    }
    
    return start + 1;
}

//Best Implementation
int removeDuplicates(int A[], int n) {
    int dupNum = 2;
    int start = dupNum - 1;
    if (n <= dupNum)
        return n;
    
    for (int i = dupNum; i < n; ++i)
    {
        if (A[start-dupNum+1] != A[i])
            A[++start] = A[i];
    }
    
    return start+1;
}

int main(int argc, char** argv)
{
    int test[] = {1,1,1};
    cout << removeDuplicates(test, sizeof(test)/sizeof(int)) << endl;
    return 0;
}

