#include <iostream>
using namespace std;

   int factorial(int n)
    {
        int ret = 1;
        for (int i = 0; i < n; ++i)
        {
            ret *= i+1;
        }
        return ret;
    }


string getPermutation0(int n, int k) {
        if (k <= 0)
            return "";
        
        string result = "";
        char* input = new char[n];
        for (int i = 0; i < n; ++i)
            input[i] = i+'1';
        
        int fac = factorial(n-1);
        k--;
        for (int i = 0; n > 0 ; k = k % fac, fac = (n >= 1)? fac/n: 1)
        {
            int count = k / fac;
            if (count >= n)
                return "";
            
            result += input[count];
            for (int j = count+1; j < n; ++j)
            {
                input[j-1] = input[j];
            }

            n--;
//            fac = (n > 1) ? fac/(n) : 1;
            
        }
       
        delete[] input;
        return result;
    }
    
//Improved solution. O(n^2) time and O(1) space(not including the space used by result string)
  string getPermutation(int n, int k) {

        int fac = 1;
       
        string result(n, '0');
        for (int i = 0; i < n; ++i) {
            fac *= i+1;
            result[i] += i+1;
        }
        
        for (int i = 0; i < n; ++i)
        {
            fac /= n - i;
            int id = (k-1) / fac;
            char cur = result[i + id];
    
            k = (k-1) % fac + 1;
          
            for (int j = i+id-1; j >= i; --j)
            {
                result[j+1] = result[j];
            }
            result[i] = cur;
        }
        
        return result;
    }

//Return the position of a given permutation.
int getPosition(string perm) {
    int n = perm.size();
    int pos = 0;
    int fac = 1;
    for (int i = 0; i < n; ++i)
        fac *= i+1;

    for (int i = 0; i < n; ++i)
    {
        fac /= n - i;
        int digit = perm[i] - '0';
        int count = 0;
        for (int j = 0; j < i; ++j)
            if (perm[j] < perm[i])
                count++;

        pos += (digit - 1 - count) * fac;
        
    }

    return pos+1;
    
}

 int main(int argc, char** argv)
{
//    cout << getPermutation(3, 2) << endl;
    cout << string(argv[1]) << ": " << getPosition(string(argv[1])) << endl;
    return 0;
}
