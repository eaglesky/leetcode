/* The knows API is defined in the parent class Relation.
      boolean knows(int a, int b); */

public class FindTheCelebrity extends Relation {
    
    //Calls the API 3(n-1) times. Space is O(1).
    //https://discuss.leetcode.com/topic/25720/java-python-o-n-calls-o-1-space-easy-to-understand-solution
    //http://www.geeksforgeeks.org/the-celebrity-problem/
    //Can be visualized using nodes and edges, where an edge
    //a -> b means a knows b. Put all nodes at the same line
    //and then you'll see why this algorithm works.
    //Can be generalized to comparing objects.
    public int findCelebrity(int n) {
        int candidate = 0;
        for (int i = 1; i < n; ++i) {
            if (knows(candidate, i)) {
                candidate = i;
            }
        }
        for (int i = 0; i < candidate; ++i) {
            if (knows(candidate, i)) {
                return -1;
            }
        }
        for (int i = 0; i < n; ++i) {
            if (i == candidate) {
                continue;
            }
            if (!knows(i, candidate)) {
                return -1;
            }
        }
        return candidate;
    }
}