#include <iostream>
#include <vector>
using namespace std;

//Traditional solution
string convert0(string s, int nRows) {
    vector<string> strs(nRows, "");
    bool reverse = true;
    int pos = 0;
    for (int i = 0; i < s.size(); ++i)
    {
        strs[pos] += s[i];

        if ((pos == nRows-1) || (pos == 0))
            reverse = !reverse;
        
        if (nRows > 1) {
            if (reverse)
                pos--;
            else
                pos++;
        }
    }

    string result = "";
    for (auto str : strs)
    {
        result += str;
    }
    return result;

}

//Math solution
//For the numbers on the straight lines, use (i, j) to denote the number on the
//(i+1)th row and (j+1)th staight line, then the index of that number in the
//original string id = (n+n-2)*j + i = (2*n-2)*j + i
//The id of the adjacent number that is on the oblique line is ida = id +
//2*(n-i-1) 
string convert(string s, int nRows) {
    if (nRows <= 1)
        return s;
    
    string result = "";
    for (int i = 0; i < nRows; ++i)
    {
        int id = i;
        for (int j = 0; id < s.size(); ++j, id = (2*nRows-2)*j+i)
        {
            result += s[id];

            if ((i > 0) && (i < nRows-1)) {
                int ida = id + 2*(nRows-i-1);
                if (ida >= s.size())
                    break;
                result += s[ida];
            }
        }
    }

    return result;
}

int main(int argc, char** argv)
{
    string s = "PAYPALISHIRING";
    cout << convert(s, 3) << endl;
    return 0;
}
