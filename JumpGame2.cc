#include <iostream>
#include <vector>
using namespace std;

//Iterative solution using greedy algorithm
//O(n) time and O(1) space
int jump(int A[], int n) {
    if (n == 0)
        return -1;

    int reach = 0;
    int step = 0;
    int prevReach = 0;
    for (int i = 0; i < n; ++i)
    {
        if (reach < i)
            return -1;
       
        if (i > prevReach) {
            step++;
            prevReach = reach;
        }

        if (A[i] + i > reach) {
            reach = A[i] + i;
        }

    }

    return step;
}

int main(int argc, char** argv)
{
    int A[] = {1, 1, 1, 1};
    cout << jump(A, sizeof(A)/sizeof(int)) << endl;
    return 0;
}
