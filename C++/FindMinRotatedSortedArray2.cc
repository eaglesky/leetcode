#include <iostream>
using namespace std;

int findMin(vector<int> &num) {
    int start = 0;
    int end = num.size() - 1;
    while (start < end) {
        int mid = start + (end - start) / 2;
        if (num[mid] > num[end])
            start = mid + 1;
        else if (num[mid] < num[end])
            end = mid;
        else
            end--;
    }
    return num[start];
}

int main(int argc, char** argv)
{
    return 0;
}