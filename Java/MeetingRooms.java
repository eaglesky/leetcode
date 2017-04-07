/* 
Given an array of meeting time intervals consisting of start and end times
[[s1,e1],[s2,e2],...] (si < ei), determine if a person could attend all meetings.

For example, Given [[0, 30],[5, 10],[15, 20]], return false.
*/
import java.util.*;

public class MeetingRooms {

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

	// O(nlogn) time and O(1) space 
	// Same as https://discuss.leetcode.com/topic/20959/ac-clean-java-solution
	public boolean canAttendMeetings(Interval[] intervals) {
		Arrays.sort(intervals, new Comparator<Interval>() {
			@Override
			public int compare(Interval interval1, Interval interval2) {
				return Integer.compare(interval1.start, interval2.start);
			}
		});
		for (int i = 1; i < intervals.length; ++i) {
			if (intervals[i].start < intervals[i-1].end) {
				return false;
			}
		}
		return true;
	}

	public static void main(String[] args) {
		MeetingRooms solution = new MeetingRooms();
		Interval[] intervals = new Interval[3];
		intervals[0] = new Interval(0, 30);
		intervals[1] = new Interval(5, 10);
		intervals[2] = new Interval(15, 20);
		System.out.println(solution.canAttendMeetings(intervals)); //false

		intervals = new Interval[2];
		intervals[0] = new Interval(5, 6);
		intervals[1] = new Interval(1, 2);
		System.out.println(solution.canAttendMeetings(intervals)); //true

		intervals = new Interval[1];
		intervals[0] = new Interval(7, 8);
		System.out.println(solution.canAttendMeetings(intervals)); //true

		intervals = new Interval[0];
		System.out.println(solution.canAttendMeetings(intervals)); //true
	}
}