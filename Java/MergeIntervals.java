/**
 * Definition for an interval.
 * public class Interval {
 *     int start;
 *     int end;
 *     Interval() { start = 0; end = 0; }
 *     Interval(int s, int e) { start = s; end = e; }
 * }
 */
public class MergeIntervals {
    
    //Sort and then merge.
    //O(nlogn) time and O(n) space
    public List<Interval> merge(List<Interval> intervals) {
        List<Interval> sortedIntervals = new ArrayList<>(intervals);
        Collections.sort(sortedIntervals, new Comparator<Interval>() {
                public int compare(Interval interval1, Interval interval2) {
                    return Integer.compare(interval1.start, interval2.start);
                }
            });
        List<Interval> result = new ArrayList<>();
        if (intervals.isEmpty()) {
            return result;
        }
        Interval prevInterval = new Interval(sortedIntervals.get(0).start, sortedIntervals.get(0).end);
        for (Interval interval : sortedIntervals) {
            if (interval.start > prevInterval.end) {
                result.add(prevInterval);
                prevInterval = interval;
            } else {
                prevInterval.end = Math.max(prevInterval.end, interval.end);
            }
        }
        result.add(prevInterval);
        return result;
    }

    //Better implementation
    public List<Interval> merge(List<Interval> intervals) {
        List<Interval> result = new ArrayList<>();
        if (intervals == null || intervals.isEmpty()) {
            return result;
        }
        Collections.sort(intervals, new Comparator<Interval>() {
                public int compare(Interval interval1, Interval interval2) {
                    return Integer.compare(interval1.start, interval2.start);
                }
        });
        for (Interval interval : intervals) {
            if (result.isEmpty() || result.get(result.size() - 1).end < interval.start) {
                result.add(interval);
            } else {
                Interval lastInterval = result.get(result.size() - 1);
                lastInterval.end = Math.max(lastInterval.end, interval.end);
            }
        }
        return result;
    }

}