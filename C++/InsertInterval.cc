#include <iostream>
#include <vector>
using namespace std;

struct Interval {
    int start;
    int end;
    Interval() : start(0), end(0) {}
    Interval(int s, int e) : start(s), end(e) {}
};

vector<Interval> insert0(vector<Interval> &intervals, Interval newInterval) {
    vector<Interval> result;

    bool inserted = false;
    for (int i = 0; (!inserted || i < intervals.size()); )
    {
        Interval curInterval ;
        if (inserted || ((i < intervals.size()) && (intervals[i].start <= newInterval.start)))
            curInterval = intervals[i++];
        else {
            curInterval = newInterval;
            inserted = true;
        }

        if (!result.empty()) {
            Interval prev = result.back();
            if (prev.end >= curInterval.start) {
                result.pop_back();
                Interval newPrev(prev.start, max(prev.end, curInterval.end));
                result.push_back(newPrev);
            } else
                result.push_back(curInterval);
        } else
            result.push_back(curInterval);

    }


    return result;
}

//Take into account the assumption that intervals are non-overlapping
vector<Interval> insert1(vector<Interval> &intervals, Interval newInterval) {
    vector<Interval> result;

    int i;
    for (i = 0; i < intervals.size(); ++i)
    {
        if ((intervals[i].end < newInterval.start))
            result.push_back(intervals[i]);
        else if ((intervals[i].start > newInterval.end))
            break;
        else {
            newInterval.start = min(intervals[i].start, newInterval.start);
            newInterval.end = max(intervals[i].end, newInterval.end);
        }
    }
    
    result.push_back(newInterval);

    for (; i < intervals.size(); ++i)
        result.push_back(intervals[i]);

    return result;
}

// More elegant logic
vector<Interval> insert(vector<Interval> &intervals, Interval newInterval) {
    
    vector<Interval> result;
    
    int i = 0;
    for (; (i < intervals.size()) && (intervals[i].start < newInterval.start); ++i)
    {
       result.push_back(intervals[i]);
    }
    
    if (result.empty() || (newInterval.start > result.back().end)) {
        result.push_back(newInterval);
    } else {
        result.back().end = max(result.back().end, newInterval.end);
    }
    
    for (; i < intervals.size(); ++i)
    {
        if (intervals[i].start > result.back().end)
            result.push_back(intervals[i]);
        else {
            result.back().end = max(result.back().end, intervals[i].end);
        }
    }
    
    return result;
}

int main(int argc, char** argv)
{
    vector<Interval> intervals = {
        Interval(1, 2),
        Interval(3, 5), 
        Interval(6, 7), 
        Interval(8, 10), 
        Interval(12, 16)
    };
    Interval newInterval(4, 9);
    vector<Interval> result = insert(intervals, newInterval);
    for (int i = 0; i < result.size(); ++i)
        cout << result[i].start << ", " << result[i].end << endl;

    return 0;
}
