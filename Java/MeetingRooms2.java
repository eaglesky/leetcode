/*
Given an array of meeting time intervals consisting of start and end times
[[s1,e1],[s2,e2],...] (si < ei), find the minimum number of conference rooms required.

For example,
Given [[0, 30],[5, 10],[15, 20]],
return 2.
*/

import java.util.*;

public class MeetingRooms2 {

	private static class Interval {
		int start;
		int end;
		Interval() {
			start = 0;
			end = 0;
		}
		Interval(int s, int e) {
			start = s;
			end = e;
		}
	}

	//Greedy algorithm. O(nlogn) time and O(n) space
	//https://discuss.leetcode.com/topic/20958/ac-java-solution-using-min-heap
	//https://segmentfault.com/a/1190000003894670
	
	//Theorem: The greedy algorithm below returns the number of rooms that is
	//always equal to the maximum number of intervals that overlap each other.
	//Proof: It is obvious that the greedy algorithm always gives a valid scheduling
	//solution, in which the number of rooms must be no less than the maximum number
	//of overlapping intervals. In other words, if there are at most n intervals 
	//overlapping each other, then we need at least n rooms, and the number of rooms
	//returned by the greedy algorithm m >= n. Now what we need to prove is why m == n.
	//Assuming m > n, then let's look at the interval x that first occupies the
	//(n+1)th room. Let the last interval of each room be y_1, y_2 ... y_n, sorted by 
	//the starting time. Since the interval x cannot be put in any of the previous rooms,
	//which means x overlap every interval in {y_i}, we have: 
	//x.start < min{y_i.end, 1 <= i <= n}. If there exists two intervals in {y_i}
	//that do not overlap each other, say y_u and y_t, (y_u.start < y_t.start), then we
	//have y_u.start < y_u.end <= y_t.start < y_t.end. According to the greedy algorithm
	//below, y_t.start <= x.start < x.end, so we have: y_u.start < y_u.end <= x.start < x.end,
	//then x does not overlap y_u, and should be able to be put in the same room as y_u rather
	//than occupying a new room, which is contradictory to our assumption.
	//Therefore, m must be equal to n, using the greedy algorithm. 

	//This solution can not only gives the minimum number of rooms, but also assign room number
	//to each interval.
    public int minMeetingRooms0(Interval[] intervals) {
        if (intervals == null || intervals.length == 0) {
            return 0;
        }
        Arrays.sort(intervals, 
        (Interval interval1, Interval interval2) -> Integer.compare(interval1.start, interval2.start));
        PriorityQueue<Integer> heap = new PriorityQueue<>();
        for (int i = 0; i < intervals.length; ++i) {
            if (heap.isEmpty() || heap.peek() <= intervals[i].start) {
                heap.poll();
            }
            heap.offer(intervals[i].end);
        }
        return heap.size();
    }

    //The above imlementation can be improved by instead of using a heap and do logn operation
    //for each element, we can sort the end times first and then sweep. This is a very
    //useful trick to reduce the constant factor of nlogn:

    //Best implementation of greedy algorithm
    //https://discuss.leetcode.com/topic/35253/explanation-of-super-easy-java-solution-beats-98-8-from-pinkfloyda
    //However compared with the previous implementation, it is harder to asign room number
    //to each interval
    public int minMeetingRooms(Interval[] intervals) {
        if (intervals == null || intervals.length == 0) {
            return 0;
        }
        Arrays.sort(intervals, 
        (Interval interval1, Interval interval2) -> Integer.compare(interval1.start, interval2.start));
        int[] endTimes = new int[intervals.length];
        int id = 0;
        for (int i = 0; i < intervals.length; ++i) {
            endTimes[id++] = intervals[i].end;
        }
        Arrays.sort(endTimes);
        int numRooms = 0;
        int endId = 0;
        for (int i = 0; i < intervals.length; ++i) {
            if (intervals[i].start < endTimes[endId]) {
                numRooms++;
            } else {
                endId++;
            }
        }
        return numRooms;
    }


    //Another greedy algorithm is based on intervals sorted by ending times. Note that when
    //checking a new interval, it must be inserted after the last finished interval if they
    //don't overlap. Picking the first finished interval may not work, example:
    // 	 |___2___|
    //	|__1__|   |_3_|
    //    |_______4_____|
    //		    |____5____|
    //If we add interval 3 after 1 but not 2, then interval 5 has to occupy a new room. However
    //a better scheduling using less rooms would be:
    // 	 |___2___||_3_|
    //	|__1__| |____5____|
    //    |_______4_____|
    //which uses three rooms rather than four rooms.


    //From the above proof we know that the minimum number of rooms required is equal to
    //the maximum number of overlapping intervals. To get the number only, we can sweep all the
    //starting times and finish times from the smallest to the largest, increase a counter
    //when the time is a starting time and decrease it when it is not. Note that we need to 
    //put the ending time before starting time if the values of time are the same. 
    //Time complexity is still O(nlogn) and space complexity is O(n).
    //https://discuss.leetcode.com/topic/38331/java-o-n-sweepline-solution
    private static class MyTime{
        final int time;
        final int isStart;
        MyTime(int time, int isStart) {
            this.time = time;
            this.isStart = isStart;
        }
    }
    
    public int minMeetingRooms1(Interval[] intervals) {
        if (intervals == null || intervals.length == 0) {
            return 0;
        }
        MyTime[] myTimes = new MyTime[intervals.length * 2];
        for (int i = 0; i < intervals.length; ++i) {
            myTimes[i*2] = new MyTime(intervals[i].start, 1);
            myTimes[i*2+1] = new MyTime(intervals[i].end, 0);
        }
        Arrays.sort(myTimes, (MyTime mt1, MyTime mt2)
        -> (mt1.time == mt2.time) ? Integer.compare(mt1.isStart, mt2.isStart)
                                  : Integer.compare(mt1.time, mt2.time));
        int count = 0;
        int numRooms = 0;
        for (int i = 0; i < myTimes.length; ++i) {
            if (myTimes[i].isStart > 0) {
                count++;
                numRooms = Math.max(numRooms, count);
            } else {
                count--;
            }
        }
        return numRooms;
    }

    public static void main(String[] args) {
    	Interval[] intervals = new Interval[] {
    		new Interval(0, 1),
    		new Interval(2, 3)
    	};
    	MeetingRooms2 solution = new MeetingRooms2();
    	int numRooms = solution.minMeetingRooms(intervals);
    	System.out.println(numRooms);
    }
}