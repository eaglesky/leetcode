#include <iostream>
#include <vector>
using namespace std;

// Using maximum subarray algorithm on the book
// Assuming matrix is m x n
// O(mn) time and O(n) space
int maximalRectangle0(vector<vector<char> > &matrix) {

    int w = matrix.size();
    if (w == 0)
        return 0;
    int l = matrix[0].size();
    if (l == 0)
        return 0;

    vector<int> maxLeft(l, -1);
    vector<int> minRight(l, l);
    vector<int> heights(l, 1);
    int maxArea = 0;
    for (int i = w-1; i >= 0; --i)
    {
        int right = l;
        int left = -1;
        for (int j = l-1; j >= 0; --j)
        {
            if ((i < w-1) && (matrix[i+1][j] == '0'))
                minRight[j] = right;
            else
                minRight[j] = min(right, minRight[j]);

            if (matrix[i][j] == '0')
                right = j;
        }

        for (int j = 0; j < l; ++j)
        {
             
            if ((i < w-1) && (matrix[i+1][j] == '0'))
                maxLeft[j] = left; 
            else
                maxLeft[j] = max(left, maxLeft[j]);

            if (matrix[i][j] == '0')
                left = j;

            if (matrix[i][j] == '0')
                heights[j] = 0;
            maxArea = max(maxArea, (minRight[j]-maxLeft[j]-1)*heights[j]);
            heights[j]++;
        }
    }

    return maxArea;
}

//Use similar solution to the Largest Rectangle in Histogram
//https://oj.leetcode.com/discuss/5198/a-o-n-2-solution-based-on-largest-rectangle-in-histogram
//O(mn) time and O(n) space
int maximalRectangle(vector<vector<char> > &matrix) {
    int w = matrix.size();
    if (w == 0)
        return 0;
    int l = matrix[0].size();
    if (l == 0)
        return 0;
    
    vector<int> heights(l+1, 0);
    int maxArea = 0;
    for (int i = 0; i < w; ++i)
    {
        vector<int> stack; 
        for (int j = 0; j <= l; ++j)
        {
            if ((matrix[i][j] == '0') || (j == l))
                heights[j] = 0;
            else
                heights[j]++;
            

            while ((!stack.empty()) && (heights[j] < heights[stack.back()])) {
                int curHeight = heights[stack.back()];
                stack.pop_back();
                int curArea = curHeight*j;
                if (!stack.empty())
                    curArea = curHeight*(j-stack.back()-1);
                maxArea = max(maxArea, curArea);
            }

            stack.push_back(j);
        }
    }
    return maxArea; 
}

int main(int argc, char** argv)
{
  /*  vector<vector<char> > test = {
        {'1', '0', '0', '1'},
        {'0', '1', '1', '0'},
        {'1', '0', '1', '0'},
        {'1', '1', '1', '1'}
    };*/

  /*  vector<vector<char> > test = {
        {'0', '1', '1', '0', '1'}, 
        {'1', '1', '0', '1', '0'},
        {'0', '1', '1', '1', '0'},
        {'1', '1', '1', '1', '0'},
        {'1', '1', '1', '1', '1'},
        {'0', '0', '0', '0', '0'}
    };//Max area = 9*/
    vector<vector<char> > test = {
        {1}   
    };

    cout << maximalRectangle(test) << endl; 
    return 0;
}
