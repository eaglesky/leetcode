#include <iostream>
#include <climits>
using namespace std;

int myatoi0(const char *str) {
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

//It's better to read the spoilers alert first before trying this problem on
//your own!
    int atoi(const char *str) {
        bool isNegative = false;
        const char* p = str;
        int result = 0;
        while (*p && (*p == ' '))
            p++;
        
        if (!*p)
            return result;
        
        if (*p == '-') {
            isNegative = true;
            p++;
        } else if (*p == '+')
            p++;
        
        for (;*p && isdigit(*p); p++)
        {
            if ((result > INT_MAX / 10)
                || ((result == INT_MAX / 10) && (((*p - '0') > 7)))){
               return isNegative ? INT_MIN : INT_MAX;
            } else {
                result = result * 10 + (*p - '0');
            }
        }
        
        return isNegative ? -result : result;
        
    }
    

int main(int argc, char** argv)
{
    cout << myatoi("-2147483647") << endl;
    cout << atoi("-223.44") << endl;
    return 0;
}
