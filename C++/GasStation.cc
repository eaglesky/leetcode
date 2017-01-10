#include <iostream>
#include <vector>
using namespace std;

//O(n) time and O(1) space
int canCompleteCircuit(vector<int> &gas, vector<int> &cost) {

        int gasInCar = 0;
        int total = 0;
        int start = 0;
        for (int i = 0; i < gas.size(); ++i) {
            gasInCar += gas[i] - cost[i];
            total += gas[i] - cost[i];
            if (gasInCar < 0) {
                gasInCar = 0;
                start = i+1;
            }
        }
    
        return (total < 0) ? -1 : start;

}

int main(int argc, char** argv)
{
    return 0;
}
