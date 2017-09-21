import java.util.*;

/**
 * Definition for a point.
 **/
 class Point {
     int x;
     int y;
     Point() { x = 0; y = 0; }
     Point(int a, int b) { x = a; y = b; }
     public String toString() {
     	return "(" + x + ", " + y + ")";
     }
 }

public class MaxPointsOnLine {

    //O(n^2) time and O(n) space
    //For each point, calculate the slope for each other point and increment 
    //the counter corresponding to the slope. The counters are stored in a 
    //map whose keys are slopes(double), which can also be represented as two
    //integers -- a normalised dividend(= original dividend / gcd(dividend, divisor))
    // and a normalised divisor. 
    public int maxPoints(Point[] points) {
        int maxNumPoints = 0;
        for (int i = 0; i < points.length; ++i) {
            Map<Double, Integer> slopeToNumPoints = new HashMap<>();
            int numCoincidence = 1;
            int curMaxNumPoints = 0;
            for (int j = i + 1; j < points.length; ++j) {
                if (points[j].y == points[i].y && points[j].x == points[i].x) {
                    numCoincidence++;
                } else {
                    double slope = 0.0;
                    if (points[j].x == points[i].x) {
                        slope = Double.POSITIVE_INFINITY;
                    } else if (points[j].y != points[i].y){
                        slope = (double)(points[j].y - points[i].y) / (points[j].x - points[i].x);
                    }
                    int numPoints = slopeToNumPoints.getOrDefault(slope, 0);
                    slopeToNumPoints.put(slope, ++numPoints);
                    curMaxNumPoints = Math.max(curMaxNumPoints, numPoints);
                }
            }
            maxNumPoints = Math.max(maxNumPoints, curMaxNumPoints + numCoincidence);
        }
        return maxNumPoints;
    }

    //Same algorithm as above, but use BigDecimal to store the slope instead of Double.
    //Using Double might miss the points when the exact values of the slopes are equal, but
    //the Double representations are not. Using BigDecimal this won't happen, but still
    //may cover more points when the slopes are close but not strictly equal. To only include
    //points whose slopes are strictly equal, we have to use GCD approach. But in practice,
    //close points should be accetable, and using BigDecimal we can have full control over
    //the precision by specifying the scale value in the divide function. The GCD approach
    //won't work if the input points have double coordinates, while the other approaches will
    //still work.
    public int maxPoints(Point[] points) {
        if (points == null || points.length == 0) {
            return 0;
        }
        int maxCount = 0;
        for (int i = 0; i < points.length; ++i) {
            int curMaxCount = 0;
            int numCoincident = 1;
            Map<BigDecimal, Integer> slopeCounts = new HashMap<>();
            for (int j = i + 1; j < points.length; ++j) {
                if (points[j].x == points[i].x && points[j].y == points[i].y) {
                    numCoincident++;
                } else {
                    BigDecimal slope = null;
                    if (points[j].x == points[i].x) {
                        slope = new BigDecimal(Long.MAX_VALUE);
                    } else {
                        BigDecimal diffY = new BigDecimal(points[j].y - points[i].y);
                        BigDecimal diffX = new BigDecimal(points[j].x - points[i].x);
                        slope = diffY.divide(diffX, 20, RoundingMode.HALF_UP);
                    }
                    int count = slopeCounts.getOrDefault(slope, 0);
                    slopeCounts.put(slope, ++count);
                    curMaxCount = Math.max(curMaxCount, count);
                }
            }
            maxCount = Math.max(maxCount, curMaxCount + numCoincident);
        }
        return maxCount;
    }

    public static void main(String[] args) {
    	MaxPointsOnLine mp = new MaxPointsOnLine();
    	Point[] points = {
    		new Point(2, 3),
    		new Point(3, 3),
    		new Point(-5, 3)
    	};
    	System.out.println(Arrays.toString(points));
        System.out.println(mp.maxPoints(points));
    }
}