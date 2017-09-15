/**
 * Definition for an interval.
 * public class Interval {
 *     int start;
 *     int end;
 *     Interval() { start = 0; end = 0; }
 *     Interval(int s, int e) { start = s; end = e; }
 * }
 */
public class InsertInterval {

	//O(n) time and O(n) space, non-inplace solution.
    public List<Interval> insert0(List<Interval> intervals, Interval newInterval) {
        List<Interval> result = new ArrayList<>();
        int i = 0;
        for (;i < intervals.size() && intervals.get(i).end < newInterval.start; result.add(intervals.get(i++)));
        Interval mergedInterval = new Interval(newInterval.start, newInterval.end);
        for(; i < intervals.size() && intervals.get(i).start <= mergedInterval.end; ++i) {
            mergedInterval.start = Math.min(mergedInterval.start, intervals.get(i).start);
            mergedInterval.end = Math.max(mergedInterval.end, intervals.get(i).end);
        }
        result.add(mergedInterval);
        for(; i < intervals.size(); result.add(intervals.get(i++)));
        return result;
    }

    //Same algorithm and performance as above, but use iterator instead of index to do iteration.
    //This is better than above because if the list is linked list, list.get(i) could be very expensive.
    public List<Interval> insert1(List<Interval> intervals, Interval newInterval) {
        List<Interval> result = new ArrayList<>();
        ListIterator<Interval> iter = intervals.listIterator();
        Interval mergedInterval = new Interval(newInterval.start, newInterval.end);
        boolean isInserted = false;
        for (;iter.hasNext();) {
            Interval curInterval = iter.next();
            if (curInterval.end < newInterval.start) {
               result.add(curInterval);
            } else if (curInterval.start <= newInterval.end) {
               mergedInterval.start = Math.min(mergedInterval.start, curInterval.start);
               mergedInterval.end = Math.max(mergedInterval.end, curInterval.end);                       
            } else {
               if (!isInserted) {
                   result.add(mergedInterval);
                   isInserted = true;
               }
               result.add(curInterval);
            }
        }
        if (!isInserted) {
            result.add(mergedInterval);
        }
        return result;
    }

    //Better implementation using iterator.
    //The helper function is important.
    
    //result stores non-overlapping intervals sorted by their start times.
    //The start time of newInterval is greater or equal to the last interval in result
    private void addInterval(List<Interval> result, Interval newInterval) {
        Interval lastInterval = (result.size() == 0) ? null : result.get(result.size() - 1);
        if (lastInterval == null || lastInterval.end < newInterval.start) {
            result.add(newInterval);
        } else {
            lastInterval.end = Math.max(lastInterval.end, newInterval.end);
        }
    }
    
    public List<Interval> insert(List<Interval> intervals, Interval newInterval) {
        List<Interval> result = new ArrayList<>();
        Iterator<Interval> iter = intervals.iterator();
        boolean isInserted = false;
        for(; iter.hasNext();) {
            Interval curInterval = iter.next();
            if (curInterval.start < newInterval.start) {
                result.add(curInterval);
            } else if (!isInserted) {
                addInterval(result, newInterval);
                addInterval(result, curInterval);
                isInserted = true;
            } else {
                addInterval(result, curInterval);
            }
        }
        if (!isInserted) {
            addInterval(result, newInterval);
        }
        return result;
    }

    //In place solution. O(n^2) time and O(1) space. Same algorithm as above.
    public List<Interval> insert(List<Interval> intervals, Interval newInterval) {
        ListIterator<Interval> iter = intervals.listIterator();
        Interval mergedInterval = new Interval(newInterval.start, newInterval.end);
        boolean isInserted = false;
        for (;iter.hasNext();) {
               Interval curInterval = iter.next();
               if (curInterval.end >= newInterval.start) {
                   if (curInterval.start <= newInterval.end) {
                        mergedInterval.start = Math.min(mergedInterval.start, curInterval.start);
                        mergedInterval.end = Math.max(mergedInterval.end, curInterval.end);
                        iter.remove();
                   } else {
                       if (!isInserted) {
                           iter.previous();
                           iter.add(mergedInterval);
                           isInserted = true;
                       }
                   }
               }
        }
        if (!isInserted) {
            iter.add(mergedInterval);
        }
        return intervals;
    }
}