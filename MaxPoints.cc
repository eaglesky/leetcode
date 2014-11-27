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
int maxPoints0(vector<Point> &points) {
    
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

//Improved version of the above solution
 int maxPoints1(vector<Point> &points) {
      int n = points.size();
      int maxNum = 0;
      for (int i = 0; i < n; ++i)
      {
          unordered_map<double, int> counts;
          int coincidentNum = 0;
          for (int j = i; j < n; ++j)
          {
              if ((points[i].x == points[j].x) && (points[i].y == points[j].y))
                  coincidentNum++;
              else if (points[i].x == points[j].x) {
                  counts[numeric_limits<double>::infinity()]++;
              } else {
                  counts[(double)(points[j].y-points[i].y)/(points[j].x-points[i].x)]++;
              }
          }
          
          if (counts.empty())
              maxNum = max(maxNum, coincidentNum);
              
          for (auto count : counts)
          {
              maxNum = max(maxNum, count.second + coincidentNum);
          }
      }
      
      return maxNum;
}

//More improved implementation
int maxPoints(vector<Point> &points) {
    int result = 0;
    
    for (int i = 0; i < points.size(); ++i)
    {
        unordered_map<double, int> slopeCounts;
        int coincidentNum = 1;
        int curMax = 0;
        for (int j = i+1; j < points.size(); ++j)
        {
            double k;
            if ((points[i].x == points[j].x) && (points[i].y == points[j].y)) {
                coincidentNum++;
            } else {
            if (points[i].x == points[j].x)
                k = numeric_limits<double>::infinity();
            else
                k = (double)(points[i].y - points[j].y) / (double)(points[i].x - points[j].x);
            slopeCounts[k]++;
            curMax = max(curMax, slopeCounts[k]);
            }
            
        }
        
        result = max(result, curMax + coincidentNum);
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
