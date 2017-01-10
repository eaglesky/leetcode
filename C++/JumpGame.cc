#include <iostream>
#include <vector>
using namespace std;

//DFS solution. TLE!
bool dfsCanJump(int A[], int n, int i)
{
    if (A[i] >= n-1-i)
        return true;
    else if (A[i] == 0)
        return false;

    for (int j = 1; j <= A[i]; ++j)
    {
        if (dfsCanJump(A, n, i+j))
            return true;
    }

    return false;
    
}

bool canJump0(int A[], int n) {
    if (n == 0)
        return false;

    return dfsCanJump(A, n, 0);
}

//Iterative solution. O(n^2) time and O(n) space
bool canJump1(int A[], int n) {
    if (n == 0)
        return false;
    vector<bool> canJumps(n, false);
    canJumps[0] = true;
    for (int i = 1; i < n; ++i)
    {
        for (int j = 0; j < i; ++j)
        {
            if (canJumps[j] && (A[j] >= i - j)) {
                canJumps[i] = true;
                break;
            }
        }
    }
    return canJumps[n-1];

}

//Best solution. O(n) time and O(1) space
bool canJump(int A[], int n)
{
    if (n == 0)
        return false;
    int maxDis = A[0];
    for (int i = 1; i < n; ++i)
    {
        if (maxDis < i)
            return false;
        else if (maxDis >= n-1)
            return true;
        maxDis = max(maxDis, i+A[i]);
    }
    return true;
}

//See other two solutions on the book
int main(int argc, char** argv)
{
    int test1[] = {2, 3, 1, 1, 4};//True
    int test2[] = {3, 2, 1, 0, 4};//False
    int test3[] = {2, 5, 0, 0};   //True
    cout << canJump(test1, sizeof(test1)/sizeof(int)) << endl;
    cout << canJump(test2, sizeof(test2)/sizeof(int)) << endl;
    cout << canJump(test3, sizeof(test3)/sizeof(int)) << endl;
    return 0;
}
