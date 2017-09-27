public class GasStation {
    
    //Two pointers solution. O(n) time and O(1) space
    public int canCompleteCircuit0(int[] gas, int[] cost) {
        int n = gas.length;
        if (n == 0 || cost.length != n) {
            return -1;
        }
        int gasLeft = 0;
        for (int start = 0, end = 0; start < n; ++start) {
            for (; gasLeft >= 0;) {
                gasLeft += gas[end] - cost[end];
                end = (end + 1) % n;
                if (end == start && gasLeft >= 0) {
                    return start;
                }
            }
            gasLeft += cost[start] - gas[start];
        }
        return -1;
    }
    
    //Improved solution
    //Need to understand two theorems:
    //(1)if station B is the first station that A can not reach,
    //then any station between A and B can not reach B. Otherwise,
    //say there is a station C that can reach B, and A must be able
    //to reach C, then A can reach B, which is contradictory to the
    //fact that "B is the first station that A cannot reach".
    //(2)If the total cost is no less than 0, then there must be a solution.
    public int canCompleteCircuit1(int[] gas, int[] cost) {
        int n = gas.length;
        if (n == 0 || cost.length != n) {
            return -1;
        }
        int gasLeft = 0;
        int start = 0;
        int totalGas = 0;
        for (int i = 0; i < n; ++i) {
            gasLeft  += gas[i] - cost[i];
            totalGas += gas[i] - cost[i];
            if (gasLeft < 0) {
                gasLeft = 0;
                start = i + 1;
            }
        }
        return (totalGas < 0) ? -1 : start;
        //When totalGas >= 0, there must be a solution according to theorem 2.
        //But why that solution starts from the id start?
        //Suppose there is a station B which is the first station that cannot
        //be reached from Start, then any station after start cannot reach B,
        //according to theorem 1, which means that there is no solution, which
        //is contradictory to the fact that there must be a solution.
    }
    
    //Best solution, easy to come up with.
    //Draw a zigzag graph, x axis represents the id of gas stations, and y axis
    //represents the amount of gas left from station 0.
    //The id of station with lowest y value if the candidate.
    //The last x value is 0, whose corresponding y value is the total gas left, 
    //which must be no less than 0 if there is a solution.
    //This graph can shows the essence of this problem, which can prove the above
    //two theorems very easily.
    public int canCompleteCircuit(int[] gas, int[] cost) {
        int n = gas.length;
        if (n == 0 || cost.length != n) {
            return -1;
        }
        int totalGasLeft = 0;
        int minId = 0;
        int minGasLeft = 0;
        for (int i = 0; i < n; ++i) {
            totalGasLeft += gas[i] - cost[i];
            if (totalGasLeft < minGasLeft) {
                minId = (i + 1) % n;
                minGasLeft = totalGasLeft;
            }
        }
        return totalGasLeft < 0 ? -1 : minId;
    }

    //Second try of above algorithm. Simpler implementation.
    public int canCompleteCircuit(int[] gas, int[] cost) {
        int result = 0;
        if (gas == null || cost == null || gas.length != cost.length || gas.length == 0) {
            return result;
        }
        int totalGas = 0;
        int minGas = 0;
        for (int i = 0; i < gas.length; ++i) {
            if (totalGas < minGas) {
                minGas = totalGas;
                result = i;
            }
            totalGas += gas[i] - cost[i];
        }
        if (totalGas < 0) {
            return -1;
        }
        return result;
    }
}