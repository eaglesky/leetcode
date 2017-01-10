#include <iostream>
using namespace std;

string addBinary(string a, string b) {
 

        int pa = a.size() - 1;
        int pb = b.size() - 1;
        
        string result = "";
        int carry = 0;
    
        for (; (pa >= 0) || (pb >= 0); pa--, pb--)
        {
            int da = (pa >= 0) ? a[pa]-'0' : 0;
            int db = (pb >= 0) ? b[pb]-'0' : 0;
            int dig = carry + da + db;
            carry = dig / 2;
            dig %= 2;
            char digc = dig + '0';
            result = digc + result;
        }
    
        if (carry > 0)
            result = '1' + result;
    
        return result;

}

int main(int argc, char** argv)
{
    return 0;
}
