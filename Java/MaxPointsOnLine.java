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