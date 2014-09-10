#include <iostream>
#include <limits>
#include <unordered_map>
#include <vector>
using namespace std;

struct Point {
    int x;
    int y;
    Point() : x(0), y(0) {}
    Point(int a, int b) : x(a), y(b) {}
};

//Pay attention to the coincident point
//O(n^2) time and O(n) space
int maxPoints(vector<Point> &points) {
    
   int n = points.size();
   if (n < 3)
       return n;

   int result = 0;

   for (int i = 0; i < n; ++i)
   {
       int coincidentPointsNum = 1;
       unordered_map<double, int> slopeCounts;
       int curMax = 0;
       for (int j = i+1; j < n; ++j)
       {
           double slope;
           if (points[i].x == points[j].x) {

               if (points[i].y == points[j].y) {
                   coincidentPointsNum++;
                   continue;
               }
                slope = numeric_limits<double>::infinity();
                
           } else {
               slope = 1.0 * (points[i].y - points[j].y) / (points[i].x - points[j].x);
           }
            
           if (slopeCounts.find(slope) == slopeCounts.end()) {
               slopeCounts[slope] = 1;
           } else {
               slopeCounts[slope]++;
           }
           
           curMax = max(curMax, slopeCounts[slope]);
       }

       result = max(result, curMax + coincidentPointsNum);
   }

   return result;
}

// Another possible approach is store the pair(a, b)/gcd(a,b) instead of (a/b)
// to represent the slope. But customized hash function for pair need to be
// implemented
// https://oj.leetcode.com/discuss/9011/c-o-n-2-solution-for-your-reference

int main(int argc, char** argv)
{
    vector<Point> points = {
        Point(84, 250),
        Point(0, 0), 
        Point(1, 0),
        Point(0, -70),
        Point(0, -70), 
        Point(1, -1),
        Point(21, 10),
        Point(42, 90),
        Point(-42, -230)
    };
    cout << maxPoints(points) << endl;
    return 0;
}
