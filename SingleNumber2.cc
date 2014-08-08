#include <iostream>
#include <algorithm>
using namespace std;

int singleNumber(int A[], int n) {
    
        int nbits = sizeof(int)*8;

        int* count = new int[nbits];
        int result = 0;
        fill_n(count, nbits, 0);
        
        for (int i = 0; i < nbits; ++i)
        {
            for (int j = 0; j < n; ++j)
            {
                if ((A[j] >> i) & 1)
                    count[i]++;
            }
            count[i] %= 3;
        }
        
        for (int i = 0; i < nbits; ++i)
        {
            result += count[i] << i;
        }
        delete[] count;
        return result;
    }


int singleNumber2(int A[], int n) {
    int one = 0;
    int two = 0;
    int three = 0;
    for (int i = 0; i < n; ++i)
    {
        two |= one & A[i];  // Here two stores bits that have 2(mod 3) 1s and 3 1s(changed from 2)
        one ^= A[i]; // Here one stores bits that have 1 1s and 3 1s(changed from 2)
        three = one & two;
        one &= ~three;
        two &= ~three;
    }
    return one;
}

void printBin(int n)
{
    for (int i = sizeof(int)*8 - 1; i >= 0; --i)
    {
        if ((n >> i) & 1)
            cout << '1';
        else
            cout << '0';

    }
    cout << endl;
}

int main(int argc, char** argv)
{
    int test[] = {0,1,0,1,0,1,99};
    printBin(99);
    cout << singleNumber(test, 7) << endl;
    cout << singleNumber2(test, 7) << endl;
    return 0;
}
