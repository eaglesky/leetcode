#include <iostream>
#include <climits>
using namespace std;

int myatoi(const char *str) {
        if (!str)
            return 0;
            
        int sum = 0;
        int sign = 1;
        const char* p = str;
        while(*p == ' ')
            p++;
        if (*p == '-') {
            sign = -1;
            p++;
        }
        else if (*p == '+') {
            sign = 1;
            p++;
        }
        
        for (; *p; ++p)
        {
            if (isdigit(*p)) {
                
                if ((sign > 0) && ((sum > INT_MAX / 10) || ((sum == INT_MAX / 10) && ((*p - '0') >= INT_MAX % 10))))
                    return INT_MAX;
                    
                if ((sign < 0) && ((sum > INT_MAX / 10) || ((sum == INT_MAX / 10) && ((*p - '0') >= (INT_MAX % 10 + 1)))))
                    return INT_MIN;
                
                
                sum = 10*sum + (*p - '0');
                
                
            } else 
                break;
        }
        
        return sum*sign;
    }

int main(int argc, char** argv)
{
    cout << myatoi("-2147483647") << endl;
    return 0;
}
