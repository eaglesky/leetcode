public class MultiplyStrings {
    
    //Return the reverse string representation of product of num and d
    private static String multiply(String num, char d, int numZeros) {
        if (d == '0' || num.equals("0")) {
            return "0";
        }
        StringBuilder productBuilder = new StringBuilder(num.length() + 1);
        for (int i = 0; i < numZeros; productBuilder.append('0'), ++i);
        int carry = 0;
        int digit = d - '0';
        for (int i = num.length() - 1; i >= 0; --i) {
            int productDigits = (num.charAt(i) - '0') * digit + carry;
            productBuilder.append((char)('0' + productDigits % 10));
            carry = productDigits / 10;
        }
        if (carry > 0) {
            productBuilder.append((char)('0' + carry));
        }
        return productBuilder.toString();
    }
    
    private static String addAllReversedStrings(String[] strs) {
        StringBuilder sumBuilder = new StringBuilder();
        int carry = 0;
        for (int i = 0; ; ++i) {
            boolean finished = true;
            int sumDigits = carry;
            for (String str : strs) {
                if (i < str.length()) {
                    finished = false;
                    sumDigits += str.charAt(i) - '0';
                }
            }
            if (finished) {
                if (carry > 0) {
                    sumBuilder.append((char)('0' + carry));
                }
                break;
            }
            sumBuilder.append((char)('0' + (sumDigits % 10)));
            carry = sumDigits / 10;
        }
        return sumBuilder.reverse().toString();
    }
    
    //Naive solution, O(mn) time and O(mn) space
    public String multiply0(String num1, String num2) {
        if (num1.length() < num2.length()) {
            return multiply(num2, num1);
        }
        String[] strs = new String[num2.length()];
        for (int i = num2.length() - 1; i >= 0; --i) {
            int id = num2.length() - i - 1;
            String str = multiply(num1, num2.charAt(i), id);
            strs[id] = str;
        }
        return addAllReversedStrings(strs);
    }
    

    //Best and simplest solution. O(mn) time and O(m+n) space
    //Faster than above solution

    //Let i', j' the indices on num1 and num2 from the right.
    //Then the value of i' also indicates the number of zeros from the right.
    //So num1[i'] * num2[j'] has at most 2 digits, which should be located at
    //index i'+j'+1, i'+j'. So we have the mapping: i', j' --> [i'+j'+1, i'+j']
    //After converting the index from the right to the index from the left:
    //m-1-i', n-1-j' --> [m+n-2-i'-j', m+n-1-i'-j']
    //Let i = m-1-i, j=n-1-j', which are the indices from the left on num1 and num2.
    //The mapping now has become: i, j -> [i+j, i+j+1]
    //So we don't have to reverse the strings. We just create a new int array nums of size
    //m + n, since the size of product is in the range of [m+n-1, m+n].
    //The product of num1[i] and num2[j] should be put to nums[i+j] and nums[i+j+1].
    //If the product of those two digits is less than 10, then nums[i+j] = 0.
    public String multiply(String num1, String num2) {
        int[] nums = new int[num1.length() + num2.length()];
        for (int i = num2.length() - 1; i >= 0; --i) {
            for (int j = num1.length() - 1; j >= 0; --j) {
                int product = nums[i + j + 1] + (num1.charAt(j) - '0') * (num2.charAt(i) - '0');
                nums[i + j + 1] = product % 10;
                nums[i + j] += product / 10; //This could be larger than 10. But it's fine,
                							 //Since the value will be reduced to less than 10
                							 //after next loop
            }
        }
        StringBuilder sb = new StringBuilder(nums.length);
        for (int num : nums) {
            if (sb.length() == 0 && num == 0) {
                continue;
            }
            sb.append((char)('0' + num));
        }
        return (sb.length() == 0) ? "0" : sb.toString();
    }
}