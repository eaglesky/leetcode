#include <iostream>
#include <vector>
using namespace std;

//Use one int to store one digit
//O(len1*len2) time and O(len1 + len2) space
string multiply0(string num1, string num2) {
    int len1 = num1.size();
    int len2 = num2.size();
    
    vector<int> result(len1+len2, 0);
    string resultStr = "";
    for (int i = 0; i < len2; ++i)
    {
        for (int j = 0; j < len1; ++j)
        {
            int d1 = num1[len1 - j - 1] - '0';
            int d2 = num2[len2 - i - 1] - '0';
            result[i+j] += d1*d2;
            result[i+j+1] += result[i+j] / 10;
            result[i+j] %= 10;
        }
    }

    int i = len1 + len2 - 1;
    for (; (i >= 0) && (result[i] == 0); --i);

    if ((i < 0) && (len1 > 0) && (len2 > 0))
        return "0";

    for (; i >= 0; --i)
    {
        resultStr += (result[i]+'0');
    }

    return resultStr;

}

#define RADIX_NUM 10000
#define RADIX 4
vector<int> str2BigInt(string num)
{
    vector<int> result;

    int len = num.size();
    if (len == 0)
        return result;

    int base = 1;
    int curNum = 0;
    for (int i = len-1; i >= 0; --i)
    {
        curNum += base*(num[i]-'0');
        base *= 10;
        if (base == RADIX_NUM) {
            result.push_back(curNum);
            curNum = 0;
            base = 1;
        }
    }

    result.push_back(curNum);

    return result;
}

vector<int> multiplyBigInts(const vector<int>& bigInt1, const vector<int>& bigInt2)
{
    int len1 = bigInt1.size();
    int len2 = bigInt2.size();

    vector<int> result(len1 + len2, 0);

    for (int i = 0; i < len2; ++i)
    {
        for (int j = 0; j < len1; ++j)
        {
            result[i+j] += bigInt1[j] * bigInt2[i];
            result[i+j+1] += result[i+j] / RADIX_NUM; 
            result[i+j] %= RADIX_NUM;
        }
    }


     

    return result;
}

//Use one int to store RADIX digits 
string multiply1(string num1, string num2) {
    vector<int> bigInt1 = str2BigInt(num1);
    vector<int> bigInt2 = str2BigInt(num2);

    vector<int> resultBigInt = multiplyBigInts(bigInt1, bigInt2);
    string result = "";

    int i = resultBigInt.size() - 1;
    for (; (i >= 0) && (resultBigInt[i] == 0); --i);

    if ((i < 0) && (num1.size() > 0) && (num2.size() > 0))
        return "0";
    int start = i;
    for (; i >= 0; --i)
    {
        string curStr = to_string(resultBigInt[i]);

        if (i < start) {

            while (curStr.size() < RADIX) {
                curStr = "0" + curStr;
            }
        }
        result += curStr;    
    }

    return result;

}

//Solution without additional space
string multiply(string num1, string num2) {
    int n1 = num1.size();
    int n2 = num2.size();
    if (n1 == 0 || n2 == 0)
        return "";
    
    string result(n1+n2, '0');
    for (int i = 0; i < n2; ++i)
    {
     
        for (int j = 0; j < n1; ++j)
        {
            int dig1 = num1[n1-1-j] - '0';
            int dig2 = num2[n2-1-i] - '0';
            int prev = result[n1+n2-1-i-j] - '0';
            result[n1+n2-1-i-j] = '0' + (dig1 * dig2  + prev) % 10;
            int next = result[n1+n2-2-i-j] - '0';
            result[n1+n2-2-i-j] = '0' + (next + (dig1 * dig2 + prev) / 10);
        }

    }
    
    int i = 0;
    for (; (i < result.size()) && (result[i] == '0'); ++i);
    
    return (i == result.size()) ? "0" : result.substr(i);
}
    

int main(int argc, char** argv)
{
    string num1(argv[1]);
    string num2(argv[2]);
    string resultStr = multiply(num1, num2);
    cout << resultStr << endl;

    return 0;
}
