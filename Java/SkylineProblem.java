public class SkylineProblem {
    
    private static class Rectangle implements Comparable<Rectangle> {
        int l;
        int r;
        int h;
        
        Rectangle(int l, int r, int h) {
            this.l = l;
            this.r = r;
            this.h = h;
        }
        
        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof Rectangle)) {
                return false;
            }
            Rectangle that = (Rectangle)other;
            return this.l == that.l && this.r == that.r && this.h == that.h;
        }
        
        public int hashCode() {
            int result = 0;
            result = 31*result + l;
            result = 31*result + r;
            result = 31*result + h;
            return result;
        }
        
        public int compareTo(Rectangle other) {
            int diff = Integer.compare(this.h, other.h);
            if (diff != 0) {
                return diff;
            }
            diff = Integer.compare(this.l, other.l);
            if (diff != 0) {
                return diff;
            }
            return Integer.compare(this.r, other.r);
        }
    }
    
    private static class KeyPoint {
        int x;
        Rectangle rec;
        KeyPoint(int x, Rectangle rec) {
            this.x = x;
            this.rec = rec;
        }
        //Sort the key points by x if x is different, otherwise
        //the starting point is higher than ending point, and the height is 
        //compared in natural order if they are all starting points
        //This makes sure that when several edges overlaps, some of which 
        //are dropping edges and rising edges, the final computed height(the highest
        //height of rising edges) is still the same as previous height, the new keypoint
        //won't be added.
        public int compareTo(KeyPoint other) {
            if (this.x == other.x) {
                int sign = (x - rec.r < 0) ? -1 : 1;
                int sign2 = (other.x - other.rec.r < 0) ? -1 : 1;
                return Integer.compare(this.rec.h * sign, other.rec.h * sign2);
            }
            return Integer.compare(this.x, other.x);
        }
    }
    
    //BST solution
    //O(nlogn) time and O(n) space
    //https://briangordon.github.io/2014/08/the-skyline-problem.html
    //https://www.youtube.com/watch?v=GSBLe8cKu0s(correct implementation of above)
    //https://discuss.leetcode.com/topic/22482/short-java-solution/28
    public List<int[]> getSkyline(int[][] buildings) {
        List<int[]> result = new ArrayList<>();

        //Can also use TreeMap<Height, Count> here to implement multi-map
        TreeSet<Rectangle> bst = new TreeSet<>();
        List<KeyPoint> sortedKeyPoints = new ArrayList<>();
        for (int i = 0; i < buildings.length; ++i) {
            Rectangle rec = new Rectangle(buildings[i][0], buildings[i][1], buildings[i][2]);
            sortedKeyPoints.add(new KeyPoint(rec.l, rec));
            sortedKeyPoints.add(new KeyPoint(rec.r, rec));
        }
        sortedKeyPoints.sort(null);
        for (int i = 0; i < sortedKeyPoints.size(); ++i) {
            int x = sortedKeyPoints.get(i).x;
            Rectangle rec = sortedKeyPoints.get(i).rec;
            if (x == rec.l) {
                bst.add(rec);
            } else {
                bst.remove(rec);
            }
            int[] curPoint = bst.isEmpty() ? new int[]{x, 0} : new int[]{x, bst.last().h};
            if (result.isEmpty()) {
                result.add(curPoint);
            } else {
                int[] prePoint = result.get(result.size()-1);
                if (prePoint[0] == curPoint[0]) {
                    prePoint[1] = curPoint[1];
                } else if (prePoint[1] != curPoint[1]) {
                    result.add(curPoint);
                }
            }
        }
        return result;
    }

    //Better implentation of above
    private static class Corner implements Comparable<Corner> {
        int x;
        int y;
        boolean isStart;
        Corner(int x, int y, boolean isStart) {
            this.x = x;
            this.y = y;
            this.isStart = isStart;
        }
        public int compareTo(Corner c) {
            return Integer.compare(this.x, c.x);
        }
    }
    
    //O(nlogn) time and O(n) space
    //When there are overlaping corner points, we can postpone adding the key points until
    //the last corner point, which is more intuitive to adding logic to the corner comparison.
    public List<int[]> getSkyline(int[][] buildings) {
        List<int[]> result = new ArrayList<>();
        if (buildings == null || buildings.length == 0 || buildings[0].length != 3) {
            return result;
        }
        List<Corner> corners = new ArrayList<>();
        for (int[] building : buildings) {
            corners.add(new Corner(building[0], building[2], true));
            corners.add(new Corner(building[1], building[2], false));
        }
        Collections.sort(corners);
        TreeMap<Integer, Integer> heightCounts = new TreeMap<>();
        for (int i = 0; i < corners.size(); ++i) {
            int count = heightCounts.getOrDefault(corners.get(i).y, 0);
            if (corners.get(i).isStart) {
                heightCounts.put(corners.get(i).y, count + 1);
            } else {
                heightCounts.put(corners.get(i).y, --count);
                if (count == 0) {
                    heightCounts.remove(corners.get(i).y);
                }
            }
            if (i == corners.size() - 1 || corners.get(i + 1).x > corners.get(i).x) {
                int curHeight = heightCounts.isEmpty() ? 0 : heightCounts.lastKey();
                if (result.isEmpty() || curHeight != result.get(result.size()-1)[1]) {
                    result.add(new int[]{corners.get(i).x, curHeight});
                }
            }
        }
        return result;
    }

    //Divide and conquer solution. O(nlogn) time and O(n) space
    //Faster than BST solution, but harder to come up with.
    private static List<int[]> getSkyLineRecur(int[][] buildings, int start, int end) {
        List<int[]> result = new ArrayList<>();
        if (start == end) {
            int[] building = buildings[start];
            result.add(new int[]{building[0], building[2]});
            result.add(new int[]{building[1], 0});
            return result;
        }
        int mid = start + (end - start) / 2;
        List<int[]> leftPoints = getSkyLineRecur(buildings, start, mid);
        List<int[]> rightPoints = getSkyLineRecur(buildings, mid + 1, end);
        int prevLeftHeight = 0, prevRightHeight = 0;
        int prevHeight = 0;
        int l = 0, r = 0;
        for(; l < leftPoints.size() && r < rightPoints.size();) {
            int[] leftPoint = leftPoints.get(l);
            int[] rightPoint = rightPoints.get(r);
            if (leftPoint[0] < rightPoint[0]) {
                int newHeight = Math.max(leftPoint[1], prevRightHeight);
                if (newHeight != prevHeight) {
                    result.add(new int[]{leftPoint[0], newHeight});
                    prevHeight = newHeight;
                }
                prevLeftHeight = leftPoint[1];
                l++;
            } else if (leftPoint[0] > rightPoint[0]) {
                int newHeight = Math.max(rightPoint[1], prevLeftHeight);
                if (newHeight != prevHeight) {
                    result.add(new int[]{rightPoint[0], newHeight});
                    prevHeight = newHeight;
                }
                prevRightHeight = rightPoint[1];
                r++;
            } else {
                int newHeight = Math.max(leftPoint[1], rightPoint[1]);
                if (newHeight != prevHeight) {
                    result.add(new int[]{leftPoint[0], newHeight});
                    prevHeight = newHeight;
                }
                prevLeftHeight = leftPoint[1];
                prevRightHeight = rightPoint[1];
                l++;
                r++;
            }
        }
        List<int[]> rest = (l == leftPoints.size()) ? rightPoints : leftPoints;
        int i = (l == leftPoints.size()) ? r : l;
        for (; i < rest.size(); ++i) {
            result.add(rest.get(i));
        }
        return result;
    }
    
    public List<int[]> getSkyline(int[][] buildings) {
        if (buildings.length == 0) {
            return new ArrayList<int[]>();
        }
        return getSkyLineRecur(buildings, 0, buildings.length - 1);
    }
}