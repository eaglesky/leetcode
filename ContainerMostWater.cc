#include <iostream>
#include <vector>
using namespace std;


int maxArea(vector<int> &height) {
    int n = height.size();
    if (n < 2)
        return 0;

    int end = n - 1;
    int start = 0;
    int maxArea = 0;
    while (start < end) {
        maxArea = max(maxArea, min(height[start], height[end])*(end-start));
        if (height[start] < height[end])
            start++;
        else
            end--;
    }
    return maxArea;
    
}

int main(int argc, char** argv)
{
    return 0;
}
