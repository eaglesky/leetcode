#include <algorithm>
#include <iostream>
#include <vector>
using namespace std;

struct Interval {
    int start;
    int end;
    Interval() : start(0), end(0) {}
    Interval(int s, int e) : start(s), end(e) {}
};

bool cmpIntervals(const Interval& interval1, const Interval& interval2)
{
    return interval1.start < interval2.start;
}

vector<Interval> merge(vector<Interval> &intervals) {
    vector<Interval> sortedIntervals = intervals;
    sort(sortedIntervals.begin(), sortedIntervals.end(), cmpIntervals);
    vector<Interval> result;

    for (int i = 0; i < sortedIntervals.size(); ++i)
    {
        if ((result.empty()) || (result.back().end < sortedIntervals[i].start))
            result.push_back(sortedIntervals[i]);
        else {
            result.back().end = max(result.back().end, sortedIntervals[i].end);
        }
    }

    return result;
}

int main(int argc, char** argv)
{
    vector<Interval> intervals = {
        Interval(1, 3), 
        Interval(2, 6), 
        Interval(8, 10), 
        Interval(15, 18)
    };

    vector<Interval> result = merge(intervals);
    
    for (int i = 0; i < result.size(); ++i)
        cout << result[i].start << ", " << result[i].end << endl;

    return 0;
}
