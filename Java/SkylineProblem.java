public class Skyline {
    
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
    }
    
    //BST solution
    //O(nlogn) time and O(n) space
    public List<int[]> getSkyline(int[][] buildings) {
        List<int[]> result = new ArrayList<>();
        TreeSet<Rectangle> bst = new TreeSet<>();
        List<KeyPoint> sortedKeyPoints = new ArrayList<>();
        for (int i = 0; i < buildings.length; ++i) {
            Rectangle rec = new Rectangle(buildings[i][0], buildings[i][1], buildings[i][2]);
            sortedKeyPoints.add(new KeyPoint(rec.l, rec));
            sortedKeyPoints.add(new KeyPoint(rec.r, rec));
        }
        sortedKeyPoints.sort(new Comparator<KeyPoint>() {
            public int compare(KeyPoint kp1, KeyPoint kp2) {
                return Integer.compare(kp1.x, kp2.x);
            }
        });
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
                    if (result.size() >= 2) {
                        curPoint = result.get(result.size()-1);
                        prePoint = result.get(result.size()-2);
                        if (curPoint[1] == prePoint[1]) {
                            result.remove(result.size()-1);
                        }
                    }
                } else if (prePoint[1] != curPoint[1]) {
                    result.add(curPoint);
                }
            }
        }
        return result;
    }
}