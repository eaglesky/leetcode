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


string getPermutation(int n, int k) {
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
    
 int main(int argc, char** argv)
{
    cout << getPermutation(3, 2) << endl;

    return 0;
}
