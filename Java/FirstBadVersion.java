/* The isBadVersion API is defined in the parent class VersionControl.
      boolean isBadVersion(int version); */

public class FirstBadVersion extends VersionControl {
    
    //Binary search. O(logn) time and O(1) space
    public int firstBadVersion(int n) {
        int low = 1;
        
        //Cannot be n+1 since it would overflow if n is max_integer.
        int high = n; 
        while (low < high) {
            int mid = low + (high - low) / 2;
            if (isBadVersion(mid)) {
                high = mid;
            } else {
                low = mid + 1;   
            }
        }
        return isBadVersion(low) ? low : 0;
    }

    //Another algorithm. Minimum calls to isBadVersion
    public int firstBadVersion(int n) {
        int id = 0;
        int low = 1;
        int high = n;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (isBadVersion(mid)) {
                id = mid;
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return id;
    }
}