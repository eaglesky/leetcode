#include <iostream>
#include <vector>
using namespace std;

//O(n) time and O(n) space
int largestRectangleArea(vector<int>& height)
{
    vector<int> stack;
    height.push_back(0);
    int maxArea = 0;
    for (int i = 0; i < height.size(); )
    {
        if (stack.empty() || height[i] > height[stack.back()]) 
            stack.push_back(i++);
        else {
            int curHeight = height[stack.back()];
            stack.pop_back();
            int prePos = stack.empty() ? -1 : stack.back();
            int curArea = curHeight * (i - prePos - 1);
            maxArea = (maxArea < curArea) ? curArea : maxArea;
        }
    }

    height.pop_back();
    return maxArea;
}

int main(int argc, char** argv)
{
    vector<int> test{1};
    cout << largestRectangleArea(test) << endl;
    return 0;
}
