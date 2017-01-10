#include <iostream>
#include <vector>
using namespace std;

//Iterative solution using greedy algorithm
//O(n) time and O(1) space
int jump0(int A[], int n) {
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

// Simplified version of the above solution
int jump(int A[], int n) {
    
    int curLastPos = A[0];
    int prevJumps = 0;
    int curJumps = 0;
    int nextLastPos = 0;
    for (int i = 1; i < n; ++i)
    {
        if (i <= curLastPos) {
            nextLastPos = max(nextLastPos, i + A[i]);
            curJumps = prevJumps + 1;
            if (i == curLastPos) {
                curLastPos = nextLastPos;
                prevJumps = curJumps;
            }
        } else {
            return INT_MAX;
        }
    }
    
    return curJumps;
}

int main(int argc, char** argv)
{
    int A[] = {1, 1, 1, 1};
    cout << jump(A, sizeof(A)/sizeof(int)) << endl;
    return 0;
}
